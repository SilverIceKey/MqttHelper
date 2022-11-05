package com.sik.mqtthelper

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.PermissionUtils.SimpleCallback
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.send_message).setOnClickListener {
            val message = MqttMessage()
            message.qos = 2
            message.payload = "测试数据".toByteArray()
            EMQXHelper.instance.mqttClient.publish("test", message)
        }
        val root =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString()
        LogUtils.i("公共路径：${root}")
        if (!PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PermissionUtils.permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .callback(object : SimpleCallback {
                    override fun onGranted() {
                        val file = File("/sdcard/log/com.sik.mqtthelper/files")
                        if (!file.exists()){
                            file.mkdirs()
                        }
                        EMQXHelper.instance.init(MqttConfig())
                    }

                    override fun onDenied() {
                    }

                }).request()
        } else {
            val file = File("/sdcard/log/com.sik.mqtthelper/files")
            if (!file.exists()){
                file.mkdirs()
            }
            EMQXHelper.instance.init(MqttConfig())
        }
    }
}