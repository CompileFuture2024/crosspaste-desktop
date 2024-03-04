package com.clipevery.dao.task

import com.clipevery.dao.task.ClipTask.Companion.getExtraInfo
import io.realm.kotlin.Realm
import io.realm.kotlin.types.RealmAny
import io.realm.kotlin.types.RealmInstant
import org.mongodb.kbson.ObjectId

class ClipTaskRealm(private val realm: Realm): ClipTaskDao {
    override suspend fun executingAndGet(taskId: ObjectId): ClipTask? {
        return realm.write {
            query(ClipTask::class, "taskId = $0", taskId).first().find()?.let {
                it.status = TaskStatus.EXECUTING
                it.modifyTime = RealmInstant.now()
                copyFromRealm(it)
            }
        }
    }

    override suspend fun success(taskId: ObjectId) {
        realm.write {
            query(ClipTask::class, "taskId = $0", taskId).first().find()?.let {
                it.status = TaskStatus.SUCCESS
                it.modifyTime = RealmInstant.now()
            }
        }
    }

    override suspend fun failAndGet(taskId: ObjectId, e: Throwable): ClipTask? {
        return realm.write {
            query(ClipTask::class, "taskId = $0", taskId).first().find()?.let {
                it.status = TaskStatus.FAILURE
                it.modifyTime = RealmInstant.now()
                getExtraInfo(it.extraInfo)?.setFailMessage(e.message ?: "Unknown error") ?: run {
                    it.extraInfo = RealmAny.Companion.create(BaseClipTaskExtraInfo().apply {
                        setFailMessage(e.message ?: "Unknown error")
                    })
                }
                return@let copyFromRealm(it)
            }
        }
    }

    override suspend fun reset(taskId: ObjectId) {
        return realm.write {
            query(ClipTask::class, "taskId = $0", taskId).first().find()?.let {
                it.status = TaskStatus.PREPARING
                it.modifyTime = RealmInstant.now()
            }
        }
    }
}