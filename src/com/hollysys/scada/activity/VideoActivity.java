package com.hollysys.scada.activity;

import java.io.File;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.hollysys.scada.R;

public class VideoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式 
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏 
        setContentView(R.layout.activity_video); 
        
        VideoView  videoView  = (VideoView) findViewById(R.id.videoView); 
        
//        File videoFile = new File("assets/HK_3.mp4");
//        if(!videoFile.exists()){
//        	Log.d("MyMessage", "文件不存在！");
//        }
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.hk_3;
//       videoView.setVideoPath("/sdcard/xyx.3gp"); 
        videoView.setVideoURI(Uri.parse(uri)); 
//        videoView.setVideoPath("/assets/svg/GongYeDianShi/HK_3.MP4");
//        videoView.setVideoPath(videoFile.getPath());
        MediaController mediaController = new MediaController(this); 
        videoView.setMediaController(mediaController); 
        videoView.requestFocus();
        videoView.start(); 
       
	}
}
