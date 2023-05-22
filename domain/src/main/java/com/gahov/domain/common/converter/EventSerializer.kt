package com.gahov.domain.common.converter

import org.json.JSONObject

class EventSerializer {

    fun extractEventParams(params: Array<Any>): String? {
        return try {
            val rawMessage = params.first() as? JSONObject

            if (rawMessage != null) {
                return rawMessage.toString()
            } else {
                null
            }

        } catch (error: Throwable) {
            null
        }
    }

    fun extractArrayParams(params: Array<Any>): String? {
        return try {
            return params.first().toString()
        } catch (error: Throwable) {
            error.printStackTrace()
            null
        }
    }
}