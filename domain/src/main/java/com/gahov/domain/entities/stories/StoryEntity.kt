package com.gahov.domain.entities.stories

data class StoryEntity(
    val id: String,
    val imageUrl: String,
    val videoUrl: String,
    var videoDuration: Long? = VIDEO_DEFAULT_DURATION,
) {
    fun isVideo() = videoUrl.isNotEmpty()

    companion object {
        const val VIDEO_DEFAULT_DURATION = 5000L
    }
}