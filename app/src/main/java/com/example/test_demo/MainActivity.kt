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

    val clientId = "cehcdiphseaa0coe0c10"
    val url = "https://1001642588942-cloudfront-z6frgspx.d-apm.com/hls/44372468-f0c5-479e-944c-9ee3e460d40d.mp4/44372468-f0c5-479e-944c-9ee3e460d40d.m3u8"

    override fun onDestroy() {
        super.onDestroy()
        MLYDriver.deactivate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerView = findViewById<StyledPlayerView>(R.id.player_view)


        MLYDriver.initialize { options ->
            options.client.id = clientId
        }

        var builder = MLYExoPlayer.Builder(playerView!!)

        val player = builder.build()
        playerView?.player = player
        player.setMediaItem(MediaItem.fromUri(url))

        playButton = findViewById(R.id.playButton)
        playButton?.setOnClickListener {
            player.playWhenReady = true
            player.prepare()
            player.play()
        }

    }
}