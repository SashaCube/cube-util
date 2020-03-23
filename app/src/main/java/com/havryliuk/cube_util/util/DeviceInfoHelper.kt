package com.havryliuk.cube_util.util

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build.*
import android.os.Build.VERSION.RELEASE
import android.os.Build.VERSION.SDK_INT
import android.provider.Settings
import android.telephony.TelephonyManager
import android.telephony.TelephonyManager.*
import androidx.core.content.ContextCompat
import java.net.NetworkInterface
import java.util.*

class DeviceInfoHelper(private val context: Context) {

    val name = deviceName

    val model: String = MODEL

    val manufacturer: String = MANUFACTURER

    val hardware: String? = HARDWARE

    val board: String? = BOARD

    val bootloader: String? = BOOTLOADER

    val user: String? = USER

    val host: String? = HOST

    val version: String = RELEASE

    val apiLevel = SDK_INT

    val id: String? = ID

    val time = TIME

    val fingerPrint: String? = FINGERPRINT

    val display: String? = DISPLAY

    val imei: String = context.imei

    val imei1: String?
        @SuppressLint("MissingPermission")
        get() {
            var imei1: String? = null
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            imei1 = telephonyManager?.imei
                        }
                } catch (e: Exception) {
                   e.printStackTrace()
                }
            }
            return imei1
        }

    val imei2: String?
        @SuppressLint("MissingPermission")
        get() {
            var imei2: String? = null
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            imei2 = telephonyManager?.getImei(1)
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return imei2
        }

    val meid: String?
        @SuppressLint("MissingPermission")
        get() {
            var meid: String? = null
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            meid = telephonyManager?.meid
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return meid
        }

    val phoneCount: Int
        @SuppressLint("MissingPermission")
        get() {
            var phoneCount = 0
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            phoneCount = telephonyManager?.phoneCount ?: 0
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return phoneCount
        }

    val phoneType: String
        @SuppressLint("MissingPermission")
        get() {
            var phoneType = "Unknown"
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            phoneType = when (telephonyManager?.phoneType) {
                                PHONE_TYPE_NONE -> "NONE"
                                PHONE_TYPE_GSM -> "GSM"
                                PHONE_TYPE_CDMA -> "CDMA"
                                PHONE_TYPE_SIP -> "SIP"
                                else -> "UNKNOWN"
                            }
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return phoneType
        }

    val simSerialNumber: String
        @SuppressLint("MissingPermission", "HardwareIds")
        get() {
            var serial = ""
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            serial = telephonyManager?.simSerialNumber ?: ""
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return serial
        }

    val networkCountryIso: String
        @SuppressLint("MissingPermission", "HardwareIds")
        get() {
            var iso = ""
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            iso = telephonyManager?.networkCountryIso ?: ""
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return iso
        }

    val simCountryIso: String
        @SuppressLint("MissingPermission", "HardwareIds")
        get() {
            var iso = ""
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            iso = telephonyManager?.simCountryIso ?: ""
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return iso
        }

    val networkOperator: String
        @SuppressLint("MissingPermission", "HardwareIds")
        get() {
            var networkOperator = ""
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            networkOperator = telephonyManager?.networkOperator ?: ""
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return networkOperator
        }

    val networkOperatorName: String
        @SuppressLint("MissingPermission", "HardwareIds")
        get() {
            var networkOperatorName = ""
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            networkOperatorName = telephonyManager?.networkOperatorName ?: ""
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return networkOperatorName
        }

    val networkType: String
        @SuppressLint("MissingPermission", "HardwareIds")
        get() {
            var networkType = ""
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            networkType = networkTypeToString(telephonyManager?.networkType)
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return networkType
        }

    val networkClass: String
        @SuppressLint("MissingPermission", "HardwareIds")
        get() {
            var networkClass = ""
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?).let { telephonyManager ->
                try {
                    if (context.isReadPhoneStatePermissionGranted())
                        if (SDK_INT >= VERSION_CODES.O) {
                            networkClass = getNetworkClass(telephonyManager?.networkType)
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return networkClass
        }

    val serialNumber: String?
        @SuppressLint("MissingPermission")
        get() = try {
            val serial =
                if (SDK_INT >= VERSION_CODES.O && context.isReadPhoneStatePermissionGranted()) {
                    getSerial()
                } else {
                    null
                }
            serial
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    inline val wiFiMacAddress
        get(): String? {
            try {
                val all: List<NetworkInterface> =
                    Collections.list(NetworkInterface.getNetworkInterfaces())
                for (nif in all) {
                    if (nif.name != WLAN) continue
                    val macBytes: ByteArray = nif.hardwareAddress ?: return null
                    val stringBuilder = StringBuilder()

                    for (b in macBytes) {
                        //res1.append(Integer.toHexString(b & 0xFF) + ":");
                        stringBuilder.append(String.format("%02X:", b))
                    }
                    if (stringBuilder.isNotEmpty()) {
                        stringBuilder.deleteCharAt(stringBuilder.length - 1)
                    }
                    return stringBuilder.toString()
                }
            } catch (ex: Exception) {
            }
            return null
        }

    /**
     *
     * Bluetooth address is only available for system apps!
     * It requires system uid or LOCAL_MAC_ADDRESS permission which is only granted by system apps!
     * Otherwise you will only get default bt mac address BluetoothAdapter.DEFAULT_MAC_ADDRESS:"02:00:00:00:00:00"
     */
    val bluetoothMacAddress: String?
        get() = Settings.Secure.getString(
            context.contentResolver,
            SECURE_SETTINGS_BLUETOOTH_ADDRESS
        ) ?: run {
            var bluetoothAddress: String? = null
            val bluetooth = BluetoothAdapter.getDefaultAdapter()
            if (bluetooth != null) {
                if (bluetooth.isEnabled) {
                    bluetooth.address?.let { address ->
                        if (address.isNotEmpty()) {
                            // Convert the address to lowercase for consistency with the wifi MAC address.
                            bluetoothAddress = address
                        }
                    }
                }
            }

            if (bluetoothAddress == DEFAULT_MAC_ADDRESS) {
                bluetoothAddress = null
            }

            bluetoothAddress
        }

    private val deviceName
        @SuppressLint("DefaultLocale")
        get() = capitalize(
            if (MODEL.toLowerCase().startsWith(MANUFACTURER.toLowerCase())) {
                MODEL
            } else {
                "$MANUFACTURER $MODEL"
            }
        )

    private fun capitalize(str: String) = str.apply {
        if (isNotEmpty()) {
            first().run { if (isLowerCase()) toUpperCase() }
        }
    }

    @Suppress("DEPRECATION")
    private val Context.imei: String
        @SuppressLint("HardwareIds", "MissingPermission")
        get() = telephonyManager?.run {
            if (isReadPhoneStatePermissionGranted()) {
                if (SDK_INT >= VERSION_CODES.O) {
                    imei
                } else {
                    deviceId
                }
            } else DEFAULT_DEVICE_ID
        } ?: DEFAULT_DEVICE_ID

    private fun Context.isReadPhoneStatePermissionGranted() =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_PHONE_STATE
        ) == PackageManager.PERMISSION_GRANTED

    private val Context.telephonyManager
        get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?

    private fun networkTypeToString(networkType: Int?): String {
        when (networkType) {
            NETWORK_TYPE_1xRTT -> return "1xRTT"
            NETWORK_TYPE_CDMA -> return "CDMA"
            NETWORK_TYPE_EDGE -> return "EDGE"
            NETWORK_TYPE_EHRPD -> return "eHRPD"
            NETWORK_TYPE_EVDO_0 -> return "EVDO rev. 0"
            NETWORK_TYPE_EVDO_A -> return "EVDO rev. A"
            NETWORK_TYPE_EVDO_B -> return "EVDO rev. B"
            NETWORK_TYPE_GPRS -> return "GPRS"
            NETWORK_TYPE_HSDPA -> return "HSDPA"
            NETWORK_TYPE_HSPA -> return "HSPA"
            NETWORK_TYPE_HSPAP -> return "HSPA+"
            NETWORK_TYPE_HSUPA -> return "HSUPA"
            NETWORK_TYPE_IDEN -> return "iDen"
            NETWORK_TYPE_LTE -> return "LTE"
            NETWORK_TYPE_UMTS -> return "UMTS"
            else -> return "Unknown"
        }
    }

    private fun getNetworkClass(networkType: Int?): String {
        return when (networkType) {
            NETWORK_TYPE_UNKNOWN -> "Unknown network"
            NETWORK_TYPE_GSM -> " GSM"
            NETWORK_TYPE_CDMA, NETWORK_TYPE_1xRTT, NETWORK_TYPE_IDEN -> " 2G"
            NETWORK_TYPE_GPRS -> " GPRS (2.5G)"
            NETWORK_TYPE_EDGE -> " EDGE (2.75G)"
            NETWORK_TYPE_UMTS, NETWORK_TYPE_EVDO_0, NETWORK_TYPE_EVDO_A, NETWORK_TYPE_EVDO_B -> " 3G"
            NETWORK_TYPE_HSPA, NETWORK_TYPE_HSDPA, NETWORK_TYPE_HSUPA -> " H (3G+)"
            NETWORK_TYPE_EHRPD, NETWORK_TYPE_HSPAP, NETWORK_TYPE_TD_SCDMA -> " H+ (3G++)"
            NETWORK_TYPE_LTE, NETWORK_TYPE_IWLAN -> " 4G"
            else -> " 4G+"
        }
    }

    companion object {
        const val DEFAULT_DEVICE_ID = ""
        const val WLAN = "wlan0"
        const val DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00"
        const val SECURE_SETTINGS_BLUETOOTH_ADDRESS = "bluetooth_address"
        const val OS = "Android"
    }
}