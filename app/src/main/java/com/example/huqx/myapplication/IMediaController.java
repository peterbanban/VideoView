package com.example.huqx.myapplication;

/**
 * Created by huqx on 2017/11/15.
 */


  public interface IMediaController {

    // 回调
    void onPause();

    void onStart();

    void onRelease();

    void onBuffering();

    void onCompletion();

    void onSoundStateChange(boolean enable);

    boolean onError();

    // 用户行为
    void pause();

    void start();

    void release();

    void seekTo(int pos);

    // 同步获取状态
    int getDuring();

    int getCurrentPosition();

    boolean isPlaying();

    boolean getSoundState();
  }


