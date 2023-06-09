package com.example.test_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.mlytics.mlysdk.driver.MLYDriver
import com.mlytics.mlysdk.driver.pheripheral.player.MLYExoPlayer

class MainActivity : AppCompatActivity() {
    private var playerView: StyledPlayerView? = null
    private var playButton: AppCompatButton? = null
    private var pauseButton: AppCompatButton? = null


    override fun onDestroy() {
        super.onDestroy()
        MLYDriver.deactivate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerView = findViewById<StyledPlayerView>(R.id.player_view)


        MLYDriver.initialize(this.applicationContext) { options ->
            options.client.id = "cegh8d9j11u91ba1u600"
            options.debug = true
        }

        var player = MLYExoPlayer.build(playerView!!)

        playerView?.player = player
        player.setMediaItem(MediaItem.fromUri("https://vsp-stream.s3.ap-northeast-1.amazonaws.com/HLS/raw/SpaceX.m3u8"))

        playButton = findViewById(R.id.playButton)
        playButton?.setOnClickListener {
            player.playWhenReady = true
            player.prepare()
            player.play()
        }

        pauseButton = findViewById(R.id.pauseButton)
        pauseButton?.setOnClickListener {
            player.pause()
        }

    }
}