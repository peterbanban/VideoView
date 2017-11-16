package com.example.huqx.myapplication;



import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoFragment videoFragment=new VideoFragment();
        FragmentManager manager=getFragmentManager();
        FragmentTransaction fragmentTransaction=manager.beginTransaction();
        ///fragmentTransaction.add(R.id.video_test, videoFragment);
        fragmentTransaction.add(R.id.video_test,videoFragment);
        fragmentTransaction.commit();
    }
}

