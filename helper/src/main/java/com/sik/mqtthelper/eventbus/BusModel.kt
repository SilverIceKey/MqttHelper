package com.sik.mqtthelper.eventbus

/**
 * EventBus传输专用类型
 */
open class BusModel<T>(val type: String, val code: Int) {
    var data: T? = null
}