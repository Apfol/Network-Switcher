package com.apfol.networkswitcher

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private final val REQUEST_CODE_ASK_PERMISSION = 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_PHONE_STATE),
                    REQUEST_CODE_ASK_PERMISSION
                )
            }
            return
        } else {
            val telephonyManager =
                getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val networkTypeText = when (telephonyManager.dataNetworkType) {
                    TelephonyManager.NETWORK_TYPE_1xRTT -> "1xRTT"
                    TelephonyManager.NETWORK_TYPE_CDMA -> "CDMA"
                    TelephonyManager.NETWORK_TYPE_EDGE -> "EDGE"
                    TelephonyManager.NETWORK_TYPE_EHRPD -> "eHRPD"
                    TelephonyManager.NETWORK_TYPE_EVDO_0 -> "EVDO rev. 0"
                    TelephonyManager.NETWORK_TYPE_EVDO_A -> "EVDO rev. A"
                    TelephonyManager.NETWORK_TYPE_EVDO_B -> "EVDO rev. B"
                    TelephonyManager.NETWORK_TYPE_GPRS -> "GPRS"
                    TelephonyManager.NETWORK_TYPE_HSDPA -> "HSDPA"
                    TelephonyManager.NETWORK_TYPE_HSPA -> "HSPA"
                    TelephonyManager.NETWORK_TYPE_HSPAP -> "HSPA+"
                    TelephonyManager.NETWORK_TYPE_HSUPA -> "HSUPA"
                    TelephonyManager.NETWORK_TYPE_IDEN -> "iDen"
                    TelephonyManager.NETWORK_TYPE_LTE -> "LTE"
                    TelephonyManager.NETWORK_TYPE_UMTS -> "UMTS"
                    TelephonyManager.NETWORK_TYPE_UNKNOWN -> "Unknown"
                    else -> throw RuntimeException("New type of network")
                }
                val preferredNetworkTypeText =
                    findViewById<TextView>(R.id.currentNetworkTypeText)
                preferredNetworkTypeText.text = networkTypeText
            }
        }
        val button = findViewById<Button>(R.id.changePreferredNetworkTypeButton)
        button.setOnClickListener {

        }
    }
}
