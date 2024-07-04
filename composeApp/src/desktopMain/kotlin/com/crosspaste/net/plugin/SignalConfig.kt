package com.crosspaste.net.plugin

import com.crosspaste.CrossPaste
import com.crosspaste.signal.SignalProcessorCache
import io.ktor.util.*

@KtorDsl
class SignalConfig {

    val signalProcessorCache: SignalProcessorCache = CrossPaste.koinApplication.koin.get()
}