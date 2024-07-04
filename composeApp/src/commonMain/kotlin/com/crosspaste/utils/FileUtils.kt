package com.crosspaste.utils

import com.crosspaste.app.AppFileType
import com.crosspaste.presist.FileInfoTree
import com.crosspaste.presist.FilesChunk
import io.ktor.utils.io.*
import java.nio.file.Path
import java.time.LocalDateTime

expect fun getFileUtils(): FileUtils

interface FileUtils {

    val tempDirectory: Path

    fun formatBytes(bytesSize: Long): String

    fun createRandomFileName(ext: String): String

    fun getExtFromFileName(fileName: String): String?

    fun createPasteRelativePath(
        appInstanceId: String,
        date: LocalDateTime = LocalDateTime.now(),
        pasteId: Long,
        fileName: String,
    ): String

    fun createPastePath(
        fileRelativePath: String,
        isFile: Boolean,
        appFileType: AppFileType,
    ): Path

    fun getFileInfoTree(path: Path): FileInfoTree

    fun getFileSize(path: Path): Long

    fun getFileMd5(path: Path): String

    fun copyPath(
        src: Path,
        dest: Path,
    ): Boolean

    fun moveFile(
        src: Path,
        dest: Path,
    ): Boolean

    fun createTempFile(
        src: Path,
        name: String,
    ): Path?

    fun createTempFile(
        srcBytes: ByteArray,
        name: String,
    ): Path?

    fun createSymbolicLink(
        src: Path,
        name: String,
    ): Path?

    fun createEmptyPasteFile(
        path: Path,
        length: Long,
    ): Boolean

    suspend fun writeFile(
        path: Path,
        byteReadChannel: ByteReadChannel,
    )

    suspend fun writeFilesChunk(
        filesChunk: FilesChunk,
        byteReadChannel: ByteReadChannel,
    )

    suspend fun readFile(
        path: Path,
        byteWriteChannel: ByteWriteChannel,
    )

    suspend fun readFilesChunk(
        filesChunk: FilesChunk,
        byteWriteChannel: ByteWriteChannel,
    )
}