package com.gahov.musenergy.data.remote.configuration.url

class BaseUrlProvider : UrlProvider {
    override fun getBaseUrl(): String {
        return BASE_URL
    }

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}