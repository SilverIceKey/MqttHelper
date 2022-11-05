package com.sik.mqtthelper

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MqttConfig() : EMQXConfig("tcp://192.168.5.254:1883", "test") {
    override var username: String
        get() = "admin"
        set(value) {}

    override var password: String
        get() = "public"
        set(value) {}

    override fun getMemoryPersistence(): MemoryPersistence {
        return MemoryPersistence()
    }

    override fun getMqttConnectOptions(): MqttConnectOptions {
        return MqttConnectOptions()
    }

    override fun getMqttCallback(): EMQXHelper.EMQXCallback {
        return object : EMQXHelper.EMQXCallback {
            override fun connectionLost(cause: Throwable?) {
                ToastUtils.showShort("消息丢失")
                LogUtils.e(cause)
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                ToastUtils.showShort("消息送达")
                LogUtils.i("主题:${topic},内容:${String(message?.payload!!)}")

            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                ToastUtils.showShort("收到确认消息")
                LogUtils.i(token?.message)

            }

        }
    }
}