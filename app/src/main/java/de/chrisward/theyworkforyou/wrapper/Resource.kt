package de.chrisward.theyworkforyou.wrapper

class Resource<T>(val status : Status, val data: T? = null, val message: String? = null) {
    enum class Status {
        ERROR, SUCCESS, LOADING
    }
}
