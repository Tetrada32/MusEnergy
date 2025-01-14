package com.gahov.musenergy.feature.stories.video.player

import androidx.media3.common.PlaybackException

interface StoryVideoPlayerController {

    fun onPlayerError(exception: PlaybackException)

    fun onPlaybackFinished()

    fun onRenderedFirstFrame(currentVideoId: String)

    fun onPlayerBuffering() {}

    fun onPlayerIdle() {}

    fun onPlayerReady() {}

}