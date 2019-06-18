package de.chrisward.theyworkforyou

import kotlinx.coroutines.*

object Coroutines {

    fun <T: Any> io(work: suspend (() -> T?)): Job =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }
}