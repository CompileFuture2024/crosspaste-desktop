package com.crosspaste.os.macos

import com.crosspaste.os.macos.api.MacosApi
import com.crosspaste.os.macos.api.MacosApi.Companion.getString

object MacosKeychainHelper {

    fun getPassword(
        service: String,
        account: String,
    ): String? {
        return getString(MacosApi.INSTANCE.getPassword(service, account))
    }

    fun setPassword(
        service: String,
        account: String,
        password: String,
    ): Boolean {
        return MacosApi.INSTANCE.setPassword(service, account, password)
    }

    fun updatePassword(
        service: String,
        account: String,
        password: String,
    ): Boolean {
        return MacosApi.INSTANCE.updatePassword(service, account, password)
    }

    fun deletePassword(
        service: String,
        account: String,
    ): Boolean {
        return MacosApi.INSTANCE.deletePassword(service, account)
    }
}
