package com.example.huqx.myapplication;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.VideoView;

/**
 * Created by huqx on 2017/11/15.
 */

public class IVideoView extends FrameLayout implements IMediaController{

  VideoView videoView;
  Uri uri;
  public  void  setUri(String uri){
    this.uri=Uri.parse(uri);
  }
  public void setVideoView(VideoView view){
      videoView=view;
  }
  public IVideoView(@NonNull Context context) {
    super(context);
  }

  public IVideoView(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public IVideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void onPause() {

  }

  @Override
  public void onStart() {

  }

  @Override
  public void onRelease() {

  }

  @Override
  public void onBuffering() {

  }

  @Override
  public void onCompletion() {

  }

  @Override
  public void onSoundStateChange(boolean enable) {

  }

  @Override
  public boolean onError() {
    return false;
  }

  @Override
  public void pause() {
     videoView.pause();
  }

  @Override
  public void start() {
     videoView.setVideoURI(uri);
     videoView.start();
     videoView.requestFocus();
  }

  @Override
  public void release() {
      videoView.stopPlayback();
  }

  @Override
  public void seekTo(int pos) {
       videoView.seekTo(pos);
  }

  @Override
  public int getDuring() {
    videoView.getDuration();
    return 0;
  }

  @Override
  public int getCurrentPosition() {
    return videoView.getCurrentPosition();
  }

  @Override
  public boolean isPlaying() {
    return videoView.isPlaying();

  }

  @Override
  public boolean getSoundState() {
    return false;
  }
}
