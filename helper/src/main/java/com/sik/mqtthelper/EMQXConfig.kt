package com.sik.mqtthelper

import com.blankj.utilcode.util.DeviceUtils
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

/**
 * emqx
 * Mqtt配置类
 * @param brokenUrl 连接地址 例如:tcp://ip:1883
 * @param topic 订阅主题
 */
abstract class EMQXConfig(val brokenUrl: String, val topic: String) {
    /**
     * 登录用户名
     */
    open var username:String = ""

    /**
     * 登录密码
     */
    open var password:String = ""

    /**
     * 设备id
     */
    open var clientId:String = DeviceUtils.getUniqueDeviceId()
    /**
     * 设置qos
     */
    open fun qos(): Int = 2

    /**
     * 获取持久化内存配置
     */
    abstract fun getMemoryPersistence(): MemoryPersistence

    /**
     * 获取连接配置
     */
    abstract fun getMqttConnectOptions(): MqttConnectOptions

    /**
     * 获取EMQX回调
     */
    abstract fun getMqttCallback(): EMQXHelper.EMQXCallback
}