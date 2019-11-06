package com.youngstudio.ex89videoviewandexoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vv= findViewById(R.id.vv);

        //Video Uri;
        Uri videoUri= Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4");


        //참고 : 내 res폴더안에 동영상이 있을 경우 ////
        videoUri= Uri.parse("android.resource://"+getPackageName() + "/" + R.raw.trailer);


        //비디오뷰의 재생, 일시정지 등을 할 수 있는 '컨트롤바'를 붙여주는 작업
        vv.setMediaController(new MediaController(this));

        // VideoView가 보여줄 동영상의 경로주소 (Uri) 설정하기
        vv.setVideoURI(videoUri);

        //동영상을 읽어오는데 시간이 걸리므로
        //비디오 로딩준비가 끝났을 때 실행하도록
        //리스너 설정
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //비디오 시작
                vv.start();
            }
        });


    }//onCreate


    //화면에 안보일때...
    @Override
    protected void onPause() {
        super.onPause();

        //비디오 일시정지
        if(vv!=null && vv.isPlaying()) vv.pause();
    }

    //액티비티가 메모리에서 사라질때..
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(vv!=null) vv.stopPlayback();
    }

    public void clickBtn(View view) {

        startActivity(new Intent(this,SecondActivity.class));
        finish();

    }


}
