package com.example.bnvvp2tlroomrcvcvtest02;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private MediaPlayer mediaPlayer01, mediaPlayer02;
//    AudioTrack audioTrack;

    ImageView imgDay, imgSetting, imgData;

    ViewPager2 mViewPager2;
    PagerAdapterHome mPagerAdapterHome;
    ImageView img1, img2, img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_today, R.id.navigation_data, R.id.navigation_setting)
//                .build();
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.nav_host_fragment);
//        NavController navController = navHostFragment.getNavController();
////        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupWithNavController(navView, navController);

        uiInit();
        setOnClickListener();

        mViewPager2.setAdapter(mPagerAdapterHome);
        mViewPager2.setCurrentItem(1, false);
        mViewPager2.setOffscreenPageLimit(1);
        mViewPager2.setUserInputEnabled(false);



        //        FirstPlay();

    }

//    private void hideAllFragment(FragmentTransaction ft) {
//        if (mHomeFragment.isVisible()) {
//            ft.hide(mHomeFragment);
//            Log.d("TAG", "hideAllFragment: 1");
//        }
//        if(dbf.isVisible()){
//            ft.hide(dbf);
//            Log.d("TAG", "hideAllFragment: 2");
//        }
//        if(nf.isVisible()){
//            ft.hide(nf);
//            Log.d("TAG", "hideAllFragment: 3");
//        }
//    }

    private void FirstPlay() {
        mediaPlayer01 = MediaPlayer.create(this, R.raw.backgroundmusic);
        mediaPlayer01.start();

//        好像不用Prepare可以直接start
//        mediaPlayer01.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//        mediaPlayer01.start();
//            }
//        });
        Looping();


    }

    private void Looping() {
        mediaPlayer02 = MediaPlayer.create(this, R.raw.backgroundmusic);
        mediaPlayer01.setNextMediaPlayer(mediaPlayer02);
        mediaPlayer01.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
                mediaPlayer01 = mediaPlayer02;
                Looping();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView1:
                mViewPager2.setCurrentItem(0);
                break;
            case R.id.imageView2:
                mViewPager2.setCurrentItem(1);;
                break;
            case R.id.imageView3:
                mViewPager2.setCurrentItem(2);
                break;
        }
    }

    private void uiInit() {
        mViewPager2 = findViewById(R.id.main_for_fragment);
        mPagerAdapterHome = new PagerAdapterHome(this);

        img1 = findViewById(R.id.imageView2);
        img2 = findViewById(R.id.imageView1);
        img3 = findViewById(R.id.imageView3);
    }

    private void setOnClickListener() {
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
    }

//    AudioTrack的方法，目前失敗
//    private void LoopPlay() throws IOException {
//        final String TEST_NAME = "testSetBufferSize";
//        final int TEST_SR = 48000;//采样频率
//        final int TEST_CONF = AudioFormat.CHANNEL_OUT_STEREO; //双声道
//        final int TEST_FORMAT = AudioFormat.ENCODING_PCM_16BIT;//一个采样点16比特
//        final int TEST_MODE = AudioTrack.MODE_STATIC;//通过write方式把数据一次一次得写到audiotrack的buffer中，比如通过编解码得到PCM数据，然后write到audiotrack
//        final int TEST_STREAM_TYPE = AudioManager.STREAM_MUSIC;
//
//        //把 WAV 檔轉成 2 進制給 AudioTrack 讀
//        final byte[] audioData;
//        InputStream initialStream = getResources().openRawResource(R.raw.backgroundmusic);
//        audioData = new byte[initialStream.available()];
//        initialStream.read(audioData);
//
//        int minBuffSize = AudioTrack.getMinBufferSize(44100,
//                AudioFormat.CHANNEL_OUT_STEREO,  AudioFormat.ENCODING_PCM_16BIT);
//        audioTrack = new AudioTrack(TEST_STREAM_TYPE, TEST_SR, TEST_CONF, TEST_FORMAT,
//                minBuffSize, TEST_MODE);
//
//        audioTrack.write(audioData, 0, audioData.length);
//        while (true){
//            audioTrack.play();
//        }
//
//    }


//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        refresh();
//    }


//    private void refresh() {
//    	finish();
//    	Intent intent = new Intent(MainActivity.this, MainActivity.class);
//    	startActivity(intent);
//    }
}