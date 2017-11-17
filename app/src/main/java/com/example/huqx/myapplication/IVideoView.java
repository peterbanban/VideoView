package com.example.huqx.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.VideoView;

/**
 * Created by huqx on 2017/11/15.
 */

public class IVideoView extends FrameLayout implements IMediaController{

  VideoView videoView;
  Uri uri;
  int duration;
  private  final  int STATE_ERROR=-1;
  private  final  int STATE_PAUSE=0;
  private  final  int STATE_PREPAERING=1;
  private  final  int STATE_PLAYING=2;
  private  final  int STATE_DESTORY=3;
  private  int STATE_FLAG=-2;


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
    STATE_FLAG=STATE_ERROR;
    return false;
  }

  @Override
  public void pause() {
     videoView.pause();
    STATE_FLAG=STATE_PAUSE;
  }

  @Override
  public void start() {
     videoView.setVideoURI(uri);
     videoView.setOnPreparedListener(new OnPreparedListener() {
       @Override
       public void onPrepared(MediaPlayer mp) {
          duration=videoView.getDuration();
         Log.d("标记视频时长",""+duration);
          if (STATE_PLAYING!=STATE_FLAG){
            STATE_FLAG=STATE_PLAYING;
            videoView.start();
            videoView.requestFocus();
        }
       }
     });

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
    start();
    if (STATE_FLAG==STATE_PLAYING) {
       pause();
     }
    return videoView.getDuration();
  }

  @Override
  public int getCurrentPosition() {
    return videoView.getCurrentPosition();
  }

  @Override
  public boolean isPlaying() {
    Log.d("检查是否在播放",""+videoView.isPlaying());
    return videoView.isPlaying();
  }

  @Override
  public boolean getSoundState() {
    return false;
  }
}
