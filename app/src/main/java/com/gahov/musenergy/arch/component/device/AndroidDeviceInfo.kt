package com.gahov.musenergy.arch.component.device

import android.os.Build
import com.gahov.domain.component.device.DeviceInfo

abstract class AndroidDeviceInfo : DeviceInfo {
    override val manufacturer: String = Build.MANUFACTURER
    override val model: String = Build.MODEL
    override val systemVersion: String = Build.VERSION.RELEASE
    override val device: String = Build.DEVICE
}