package cn.tedu.musicplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;

import cn.tedu.musicplayer.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.musicplayer.app.MyApp;
import cn.tedu.musicplayer.entity.Song;
import cn.tedu.musicplayer.entity.Song_list;
import cn.tedu.musicplayer.fragment.HotMusicListFragment;
import cn.tedu.musicplayer.fragment.NewMusicListFragment;
import cn.tedu.musicplayer.model.BitmapCallback;
import cn.tedu.musicplayer.model.MusicModel;
import cn.tedu.musicplayer.model.SongInfoCallBack;
import cn.tedu.musicplayer.ui.CircleImageView;
import cn.tedu.musicplayer.util.BitmapUtils;
import cn.tedu.musicplayer.util.ImageUtil;


public class MainActivity extends AppCompatActivity {
    private ViewPager vpContainer;
    private RadioGroup rgRadioGroup;
    private RadioButton rbNew;
    private RadioButton rbHot;
    private List<Fragment>fragments;
    public TextView tvCMTitle;
    public CircleImageView ivCMPic;
    public MediaPlayer mediaPlayer;
    private RelativeLayout rlPlayMusic;
    public TextView tvPMTitle, tvPMSinger, tvPMLrc, tvPMCurrentTime, tvPMTotalTime;
    public ImageView ivPMBackground, ivPMAlbum;
    public SeekBar seekBar;
    private MyApp app;
    private MusicModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        lead();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setPagerAdapter();
        setListener();
        setmediaPlayer();
        setSeekBar();
        app = MyApp.getApp();
        model=new MusicModel();
    }



    public void lead(){
        //定义第一次引导页面
        //设置初次登录标志位
        Boolean isFirstIn = false;
        SharedPreferences pref = getSharedPreferences("myActivityName", 0);
//取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = pref.getBoolean("isFirstIn", true);

        //
        //SharedPreferences pref = getSharedPreferences("myActivityName", 0);
        if(isFirstIn == true)
        {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,activity_lead.class);
            startActivity(intent);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();

        }
    }
    public void setmediaPlayer()
    {
        mediaPlayer=new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(app.getSongs()!=null)
                {nextMusic();}
            }
        });
    }
    /**
     * 暂停或播放
     */
    public void startOrPause() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else{
            mediaPlayer.start();
        }
    }
    public void controllMusic(View view){
        switch (view.getId()){
            case R.id.ivPMStart://播放按钮
                startOrPause();
                break;
            case R.id.ivPMNext://下一首
                nextMusic();
                break;
            case R.id.ivPMPre://上一首
                preMusic();
                break;
            default:

        }
    }
    /*
    **上一首
     */
    public void preMusic()
    {
        app.preMusic();
        Song_list currentMusic = app.getCurrentMusic();
        currentMusic = app.getCurrentMusic();
        model.loadSongInfo(currentMusic.getSong_id(), new SongInfoCallBack() {
            @Override
            public void onSongInfoLoaded(Song song) {
                //播放音乐
                String titile=song.getSonginfo().getTitle();
                tvCMTitle.setText(titile);
                String smallPicPath=song.getSonginfo().getPic_small();
                BitmapUtils.loadBitmap(getApplicationContext(),smallPicPath, new BitmapCallback(){
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap) {
                        if(bitmap!=null){
                            ivCMPic.setImageBitmap(bitmap);
                            //让imageView转起来
                            RotateAnimation anim = new RotateAnimation(0, 360, ivCMPic.getWidth()/2, ivCMPic.getHeight()/2);
                            anim.setDuration(10000);
                            //匀速旋转
                            anim.setInterpolator(new LinearInterpolator());
                            //无限重复
                            anim.setRepeatCount(Animation.INFINITE);
                            ivCMPic.startAnimation(anim);
                        }

                        else{
                            ivCMPic.setImageResource(R.mipmap.ic_launcher);
                        }
                    }

                });
                tvPMTitle.setText(song.getSonginfo().getTitle());
                tvPMSinger.setText(song.getSonginfo().getAuthor());
                playMusic(song.getBitrate().getFile_link());
                ImageUtil.setPlayImage(app.getApplicationContext(),song.getSonginfo().getPic_premium(), ivPMAlbum, ivPMBackground);

            }
        });
    }
    /*
    **把下一首写到函数里面
     */
    public void nextMusic()
    {
        app.nextMusic();
        Song_list currentMusic = app.getCurrentMusic();
        model.loadSongInfo(currentMusic.getSong_id(), new SongInfoCallBack() {
            @Override
            public void onSongInfoLoaded(Song song) {
                //播放音乐
                String titile = song.getSonginfo().getTitle();
                tvCMTitle.setText(titile);
                String smallPicPath = song.getSonginfo().getPic_small();
                BitmapUtils.loadBitmap(getApplicationContext(), smallPicPath, new BitmapCallback() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap) {
                        if (bitmap != null) {
                            ivCMPic.setImageBitmap(bitmap);
                            //让imageView转起来
                            RotateAnimation anim = new RotateAnimation(0, 360, ivCMPic.getWidth() / 2, ivCMPic.getHeight() / 2);
                            anim.setDuration(10000);
                            //匀速旋转
                            anim.setInterpolator(new LinearInterpolator());
                            //无限重复
                            anim.setRepeatCount(Animation.INFINITE);
                            ivCMPic.startAnimation(anim);
                        } else {
                            ivCMPic.setImageResource(R.mipmap.ic_launcher);
                        }
                    }

                });
                playMusic(song.getBitrate().getFile_link());
                ImageUtil.setPlayImage(app.getApplicationContext(), song.getSonginfo().getPic_premium(), ivPMAlbum, ivPMBackground);
                tvPMTitle.setText(song.getSonginfo().getTitle());
                tvPMSinger.setText(song.getSonginfo().getAuthor());
            }

        });
        }

    public void playMusic(String url)
    {
        try {
           mediaPlayer.reset();
           mediaPlayer.setDataSource(url);
           mediaPlayer.prepareAsync();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void setListener() {
        //给圆形ImageView添加事件监听 点击后弹出rlPlayMusic
        ivCMPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rlPlayMusic.setVisibility(View.VISIBLE);
                //使用动画显示整个rlPlayMusic
                ScaleAnimation anim = new ScaleAnimation(0, 1, 0, 1, 0, rlPlayMusic.getHeight());
                anim.setDuration(300);
                rlPlayMusic.startAnimation(anim);
            }
        });
        //给rlPlayMusic添加touch事件  拦截事件
        rlPlayMusic.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                //自己消费touch事件
                return true;
            }
        });

       vpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
               switch (position){
               case 0:
                   rbNew.setChecked(true);
                   break;
               case 1:
                   rbHot.setChecked(true);
                   break;
           }

           }




           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });

       rgRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i)
            {
                case R.id.rb_new:
                    vpContainer.setCurrentItem(0);
                    break;
                case R.id.rb_hot:
                    vpContainer.setCurrentItem(1);
                    break;
            }
           }
       });
        {
        }
    };

    private void setPagerAdapter() {
        fragments=new ArrayList<>();
        fragments.add(new NewMusicListFragment());
        fragments.add(new HotMusicListFragment());
        //创建PagerAdapter
        PagerAdapter pagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
        vpContainer.setAdapter(pagerAdapter);
    }


    /**
     * 初始化控件
     */
    private void setViews() {
        vpContainer=findViewById(R.id.vp_container);
        rgRadioGroup=findViewById(R.id.rg_radiogroup);
        rbNew=findViewById(R.id.rb_new);
        rbHot=findViewById(R.id.rb_hot);
        tvCMTitle=findViewById(R.id.tvCMTitle);
        ivCMPic=findViewById(R.id.ivCMPic);
        rlPlayMusic =  findViewById(R.id.rlPlayMusic);
        tvPMTitle =  findViewById(R.id.tvPMTitle);
        tvPMSinger =  findViewById(R.id.tvPMSinger);
        tvPMLrc =  findViewById(R.id.tvPMLrc);
        tvPMCurrentTime =  findViewById(R.id.tvPMCurrentTime);
        tvPMTotalTime =  findViewById(R.id.tvPMTotalTime);
        ivPMBackground =  findViewById(R.id.ivPMBackground);
        ivPMAlbum =  findViewById(R.id.ivPMAlbum);
        seekBar =  findViewById(R.id.seekBar);



    }
class MainPagerAdapter extends FragmentPagerAdapter
    {
        public MainPagerAdapter(FragmentManager fm)
        {super(fm);}

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
        public int getCount()
        {
            return fragments.size();
        }
    }

    @Override
public void onBackPressed()
{
    if(rlPlayMusic.getVisibility()==View.VISIBLE)
    {
        rlPlayMusic.setVisibility(View.INVISIBLE);
        ScaleAnimation anim = new ScaleAnimation(1,0,1,0,0,rlPlayMusic.getHeight());
        anim.setDuration(300);
        rlPlayMusic.startAnimation(anim);
    }
    else
        {
            super.onBackPressed();
        }
}
//setSeekBar
public void setSeekBar()
    {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
                mediaPlayer.seekTo(seekBar.getProgress());//在当前位置播放
            }
        });
    }
}
