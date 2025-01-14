package com.gahov.musenergy.feature.stories.video.player

import android.content.Context
import androidx.annotation.OptIn
import androidx.lifecycle.Lifecycle
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.Player.REPEAT_MODE_OFF
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.HttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.SimpleCache
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import javax.inject.Inject

@OptIn(UnstableApi::class)
class StoriesPlayerManager @Inject constructor(
    private val context: Context,
    simpleCacheProvider: SimpleCache
) : Player.Listener {

    private var playWhenReady: Boolean

    private var manualPause: Boolean = false

    var simpleExoPlayer: ExoPlayer? = null

    private var presenter: StoryVideoPlayerController? = null

    private var playerView: PlayerView? = null

    var isPlayerInitialized: Boolean = false

    var lifecycle: Lifecycle? = null

    var currentVideoId: String = ""

    private lateinit var httpDataSourceFactory: HttpDataSource.Factory

    private lateinit var cacheDataSourceFactory: DataSource.Factory
    private val simpleCache: SimpleCache = simpleCacheProvider

    init {
        this.playWhenReady = true
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        presenter?.onPlayerError(error)
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            ExoPlayer.STATE_ENDED -> presenter?.onPlaybackFinished()
            Player.STATE_BUFFERING -> presenter?.onPlayerBuffering()
            Player.STATE_IDLE -> presenter?.onPlayerIdle()
            Player.STATE_READY -> presenter?.onPlayerReady()
        }
    }

    override fun onRenderedFirstFrame() {
        super.onRenderedFirstFrame()
        presenter?.onRenderedFirstFrame(currentVideoId)
    }

    fun initializePlayer(presenter: StoryVideoPlayerController) {
        if (!isPlayerInitialized) {

            httpDataSourceFactory = DefaultHttpDataSource.Factory()
                .setAllowCrossProtocolRedirects(true)

            cacheDataSourceFactory = DefaultDataSource.Factory(
                context, httpDataSourceFactory
            )

            cacheDataSourceFactory = CacheDataSource.Factory()
                .setCache(simpleCache)
                .setUpstreamDataSourceFactory(httpDataSourceFactory)
                .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)

            simpleExoPlayer = ExoPlayer.Builder(context)
                .setMediaSourceFactory(DefaultMediaSourceFactory(cacheDataSourceFactory))
                .build()

            simpleExoPlayer?.let {
                with(it) {
                    repeatMode = REPEAT_MODE_OFF
                    volume = 1.0f
                    addListener(this@StoriesPlayerManager)
                    playerView?.player = it
                }
            }
            registerPlayerController(presenter)
            isPlayerInitialized = true
            simpleExoPlayer?.prepare()
        }
    }

    fun setMediaSource(
        id: String,
        videoUrl: String,
        playerView: PlayerView
    ) {
        this.playerView = playerView
        simpleExoPlayer?.let {
            this.playerView?.player = it
            with(it) {
                setMediaSource(createMediaSourceItem(videoUrl))
                currentVideoId = id
                playWhenReady = true
            }
        }
    }

    private fun createMediaSourceItem(url: String): MediaSource {
        return if (url.contains(HSL)) createHlsMediaSource(url) else createMediaSource(url)
    }

    private fun registerPlayerController(presenter: StoryVideoPlayerController) {
        this.presenter = presenter
    }

    private fun createMediaSource(url: String): MediaSource {
        return ProgressiveMediaSource.Factory(cacheDataSourceFactory)
            .createMediaSource(MediaItem.fromUri(url))
    }

    private fun createHlsMediaSource(url: String): MediaSource {
        return HlsMediaSource.Factory(cacheDataSourceFactory)
            .createMediaSource(MediaItem.fromUri(url))
    }

    fun releasePlayer() {
        if (isPlayerInitialized) {
            simpleExoPlayer?.let { exoPlayer ->
                playerView?.player = null
                playWhenReady = exoPlayer.playWhenReady
                exoPlayer.removeListener(this)
                exoPlayer.clearMediaItems()
                presenter = null
                lifecycle = null
                exoPlayer.release()
            }
            simpleExoPlayer = null
            isPlayerInitialized = false
        }
    }

    fun pause(isManualPause: Boolean = false) {
        if (simpleExoPlayer?.isPlaying == true) {
            simpleExoPlayer?.pause()
            manualPause = isManualPause
        }
    }

    fun play() {
        if (simpleExoPlayer?.isPlaying == false) {
            if (manualPause) {
                manualPause = false
            } else {
                simpleExoPlayer?.play()
            }
        }
    }

    fun getPlayerView(): PlayerView? {
        return playerView
    }

    companion object {
        const val HSL = "?fm=hls"
    }
}