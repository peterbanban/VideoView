package com.example.huqx.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;


public class VideoFragment extends android.app.Fragment implements OnClickListener{

  IVideoView iVideoView;
  VideoView videoView ;
  AppCompatSeekBar acProcess;
  TextView  timeEnd;
  TextView timeStart;
  ImageButton ibControll;
  RelativeLayout playControll;
  RelativeLayout screenTouch;
  int playState=0;
  int seekToPosition;
  int SCRRENLIGHT=0;
  private OnFragmentInteractionListener mListener;

  public VideoFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.fragment_video,container,false);
      videoView=view.findViewById(R.id.vv_player);
      acProcess=view.findViewById(R.id.ac_process);
      timeEnd=view.findViewById(R.id.tv_end);
      timeStart=view.findViewById(R.id.tv_start);
      ibControll=view.findViewById(R.id.ib_controller);
      playControll=view.findViewById(R.id.rl_play_controller);
      screenTouch=view.findViewById(R.id.rl_screen);
      Log.d("设置点击监听","标记");
      acProcess.setClickable(true);
      playControll.setClickable(true);
      screenTouch.setClickable(true);
      ibControll.setOnClickListener(this);
      acProcess.setOnClickListener(this);
      playControll.setOnClickListener(this);
      screenTouch.setOnClickListener(this);
      screenTouch.setOnClickListener(this);

      iVideoView.setVideoView(videoView);
      iVideoView.setUri("http://www.cnblogs.com/yangqiangyu/p/5167812.html");

    return view;
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    //videoView.setOnClickListener(this);
    Log.d("新标记","123");
    iVideoView=new IVideoView(context);

  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override
  public void onClick(View v) {
    Log.d("进入点击事件","标记");
      switch (v.getId()){
        case R.id.rl_screen:
             screenOn();
        case R.id.ib_controller:
            if (playState==0){
              videoView.start();
              playState=1;
              ibControll.setImageResource(R.drawable.detail_play);
            }else
            {
              videoView.pause();
              playState=0;
              ibControll.setImageResource(R.drawable.detail_pause);
            }
            screenOff();
          Log.d("标记","有反应");
            break;
        case R.id.ac_process:

             break;
        case R.id.vv_player:
            screenOff();
             break;
        default:
          screenOff();
      }
  }

  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
  public void screenOff(){
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    ibControll.setVisibility(View.INVISIBLE);
    playControll.setVisibility(View.INVISIBLE);
    SCRRENLIGHT=0;
  }
  public void screenOn(){
    playControll.setVisibility(View.VISIBLE);
    ibControll.setVisibility(View.VISIBLE);
    SCRRENLIGHT=1;
  }
}
