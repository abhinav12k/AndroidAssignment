package com.baymax.socialfeed.util

/**
 * Created by abhinav on 18/11/21.
 */
sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T?): Resource<T>(data,null)
    class Error<T>(message: String?): Resource<T>(null,message)
}