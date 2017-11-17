package com.example.huqx.myapplication;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;


public class VideoFragment extends android.app.Fragment implements OnClickListener {

  IVideoView iVideoView;
  VideoView videoView;
  AppCompatSeekBar acProcess;
  TextView timeEnd;
  TextView timeStart;
  ImageButton ibController;
  RelativeLayout playController;
  RelativeLayout screenTouch;
  int playState = 0;
  int SCRRENLIGHT = 0;

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
    View view = inflater.inflate(R.layout.fragment_video, container, false);
    videoView = view.findViewById(R.id.vv_player);
    acProcess = view.findViewById(R.id.ac_process);
    timeEnd = view.findViewById(R.id.tv_end);
    timeStart = view.findViewById(R.id.tv_start);
    playController = view.findViewById(R.id.rl_play_controller);
    ibController = view.findViewById(R.id.ib_controller);
    screenTouch = view.findViewById(R.id.rl_screen);
    Log.d("设置点击监听", "标记");
    acProcess.setClickable(true);
    playController.setClickable(true);
    screenTouch.setClickable(true);
    videoView.setClickable(true);
    videoView.setOnClickListener(this);
    ibController.setOnClickListener(this);
    acProcess.setOnClickListener(this);
    playController.setOnClickListener(this);
    screenTouch.setOnClickListener(this);
    iVideoView.setVideoView(videoView);

    iVideoView.setUri("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");

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
    iVideoView = new IVideoView(context);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {

    videoView.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        screenOn();
        screenOff();
        return false;
      }
    });
    acProcess.setMax(1000);
    Log.d("seekbar", "值是" + iVideoView.getDuring());
    Log.d("seekbar", "值是" + iVideoView.getCurrentPosition());
    acProcess.setProgress(iVideoView.getCurrentPosition() / iVideoView.getDuring() * 1000);
//    timeEnd.setText(iVideoView.getDuring());
//    timeStart.setText(iVideoView.getCurrentPosition());
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.ib_controller:
        screenOn();
        if (playState == 0) {
//          iVideoView.start();
          videoView.start();
          Log.d(TAG, "onClick: 检查是否正在播放"+videoView.isPlaying());
          updateUi();
          playState = 1;
          ibController.setImageResource(R.drawable.detail_pause);
        } else {
          iVideoView.pause();
          playState = 0;
          ibController.setImageResource(R.drawable.detail_play);
        }
        screenOff();
        break;
      case R.id.ac_process:
        screenOn();
        break;
      case R.id.vv_player:
        screenOn();
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

  public void screenOn() {
    ibController.setVisibility(View.VISIBLE);
    playController.setVisibility(View.VISIBLE);
    acProcess.setVisibility(View.VISIBLE);
    SCRRENLIGHT = 0;
  }

  public void screenOff() {
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        ibController.setVisibility(View.INVISIBLE);
        playController.setVisibility(View.INVISIBLE);
        SCRRENLIGHT = 1;
      }
    }, 3000);
  }

  public void updateUi(){
    Handler handler=new Handler();
    handler.post(new Runnable() {
      @Override
      public void run() {
        for (int i=0;i<20;i++){
               timeStart.setText(videoView.getCurrentPosition()+"");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }}
    });

//    new android.os.Handler().postDelayed(new Runnable() {
//      @Override
//      public void run() {
//        Log.d(TAG, "run: 准备更新ui"+videoView.isPlaying());
//           while (iVideoView.isPlaying()) {
//             timeStart.setText(iVideoView.getCurrentPosition() + "123");
//             Log.d(TAG, "run: 标记:" + videoView.isPlaying());
//             try {
//               Thread.sleep(1000);
//             } catch (InterruptedException e) {
//               e.printStackTrace();
//             }
//           }
//      }
//    },1000);
  }
}
