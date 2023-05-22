package com.gahov.domain.component.device

interface DeviceInfo {
    val model: String
    val manufacturer: String
    val systemVersion: String
    val device: String
    val versionCode: Int
    val versionName: String
}