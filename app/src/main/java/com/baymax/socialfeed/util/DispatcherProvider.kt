package com.baymax.socialfeed.util

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by abhinav on 18/11/21.
 */
interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}