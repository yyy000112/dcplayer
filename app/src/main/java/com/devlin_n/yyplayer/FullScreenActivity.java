package com.devlin_n.yyplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.devlin_n.library.FloatWindowManager;
import com.devlin_n.yin_yang_player.controller.StandardVideoController;
import com.devlin_n.yin_yang_player.player.YinYangPlayer;

/**
 * 全屏播放
 * Created by Devlin_n on 2017/4/21.
 */

public class FullScreenActivity extends AppCompatActivity{

    private YinYangPlayer magicVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        magicVideoView = new YinYangPlayer(this);
        setContentView(magicVideoView);
        magicVideoView
                .autoRotate()
                .alwaysFullScreen()
//                .useAndroidMediaPlayer()
                .setTitle("这是一个标题")
                .setUrl("http://flv2.bn.netease.com/videolib3/1611/28/GbgsL3639/HD/movie_index.m3u8")
                .setVideoController(new StandardVideoController(this))
                .setScreenType(YinYangPlayer.SCREEN_TYPE_16_9)
                .start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        magicVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        magicVideoView.resume();
        magicVideoView.stopFloatWindow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        magicVideoView.release();
    }

    @Override
    public void onBackPressed() {
        if (!magicVideoView.onBackPressed()){
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FloatWindowManager.PERMISSION_REQUEST_CODE) {
            if (FloatWindowManager.getInstance().checkPermission(this)) {
                magicVideoView.startFloatWindow();
            } else {
                Toast.makeText(FullScreenActivity.this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
            }
        }
    }
}