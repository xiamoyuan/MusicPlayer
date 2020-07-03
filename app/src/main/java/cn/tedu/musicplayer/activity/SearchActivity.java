package cn.tedu.musicplayer.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import cn.tedu.musicplayer.R;
import cn.tedu.musicplayer.adapter.QueryMusicAdapter;
import cn.tedu.musicplayer.app.MyApp;
import cn.tedu.musicplayer.entity.SearchSongInfo;
import cn.tedu.musicplayer.entity.Song;
import cn.tedu.musicplayer.model.LrcCallback;
import cn.tedu.musicplayer.model.MusicModel;
import cn.tedu.musicplayer.model.QuerySongCallback;
import cn.tedu.musicplayer.model.SongInfoCallBack;
import cn.tedu.musicplayer.ui.CircleImageView;
import cn.tedu.musicplayer.model.BitmapCallback;
import cn.tedu.musicplayer.util.BitmapUtils;
import cn.tedu.musicplayer.util.ImageUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class SearchActivity extends AppCompatActivity {

    private ListView lvNewMusic;
    private SearchSongInfo currentMusic;
    private ImageView ivPMStart,ivCMpause;
    public TextView tvCMTitle;
    public CircleImageView ivCMPic;
    private RelativeLayout rlPlayMusic;
    public TextView tvPMTitle;
    public TextView tvPMSinger,tvCMmodel;
    private TextView tvPMLrc;
    private TextView tvPMCurrentTime;
    private TextView tvPMTotalTime;
    public ImageView ivPMBackground, ivPMAlbum;
    private SeekBar seekBar;
    private MyApp app;
    private MusicModel model;
    private QueryMusicAdapter adapter;
    private List<SearchSongInfo> searchSongInfos;
    private MediaPlayer mediaPlayer;
    private Song m1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setViews();
        setmediaPlayer();
        setSeekBar();
        app = MyApp.getApp();
        app.setModel(0);
        //调用业务层，加载所有新歌榜音乐
        model = new MusicModel();
        model.loadSongSearch(getIntent().getStringExtra("keyword"), new QuerySongCallback() {
            @Override
            public void QuerySongLoad(List<SearchSongInfo> searchSongInfos) {
                try{
                    //拿到网络上的json数据
                    SearchActivity.this.searchSongInfos = searchSongInfos;
                    //音乐列表加载完成后,将List存入Application
                    app.setSearchSongInfo(searchSongInfos);
                    setAdapter();
                } catch (Exception e){
                    Toast toast = Toast.makeText(SearchActivity.this, "无网络连接或无相关歌曲", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);//居中显示
                    toast.show();
                }
            }
        });
        //musicTime();
        setListener();
    }

    public String calculateTime(int time){
        int minute;
        int second;
        if(time > 60){
            minute = time / 60;
            second = time % 60;
            //分钟再0~9
            if(minute >= 0 && minute < 10){
                //判断秒
                if(second >= 0 && second < 10){
                    return "0"+minute+":"+"0"+second;
                }else {
                    return "0"+minute+":"+second;
                }
            }else {
                //分钟大于10再判断秒
                if(second >= 0 && second < 10){
                    return minute+":"+"0"+second;
                }else {
                    return minute+":"+second;
                }
            }
        }else if(time < 60){
            second = time;
            if(second >= 0 && second < 10){
                return "00:"+"0"+second;
            }else {
                return "00:"+ second;
            }
        }
        return null;
    }

    public void musicTime() {
        new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if(mediaPlayer!=null&&mediaPlayer.isPlaying())
                    {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                //更新UI
                                int position = mediaPlayer.getCurrentPosition();
                                tvPMCurrentTime.setText(calculateTime(position/1000));
                                seekBar.setProgress(position);
                                //m1=app.getSearchSong();
                                HashMap<String, String> lrc = m1.getLrc();
                                if(lrc!=null) { //歌词是存在的
                                    String content = lrc.get(calculateTime(position/1000));
                                    if (content != null) { //当前时间需要更新歌词
                                        tvPMLrc.setText(content);
                                    }
                                }

                            }

                        });
                    }
                }
            },0,50);
    }

    //设置播放初始化
    public void setmediaPlayer() {
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
                if(app.getSearchSongs()!=null)
                {
                    if(app.getModel()==0)
                        nextMusic();
                    if(app.getModel()==1)
                        //随机播放
                        randomMusic();
                    if(app.getModel()==2)
                        //单曲循环
                        thisMusic();
                }
            }
        });
    }

    /*
     **上一首
     */
    public void preMusic() {
        tvPMLrc.setText("Unknow");
        app.preSearchMusic();
        currentMusic = app.getSearchCurrentMusic();
        model.loadSongInfo(currentMusic.getSongid(), new SongInfoCallBack() {
            @Override
            public void onSongInfoLoaded(Song song) {
                try {
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
                    tvPMTitle.setText(song.getSonginfo().getTitle());
                    tvPMSinger.setText(song.getSonginfo().getAuthor());
                    playMusic(song.getBitrate().getFile_link());
                    lrcDowload(song);
                    musicTime();
                    ImageUtil.setPlayImage(app.getApplicationContext(), song.getSonginfo().getPic_premium(), ivPMAlbum, ivPMBackground);
                } catch (NullPointerException e) {
                    Toast toast = Toast.makeText(SearchActivity.this, "没有当前歌曲的资源", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);//居中显示
                    toast.show();
                }
            }
        });
    }
    /*
     **把下一首写到函数里面
     */
    public void nextMusic() {
        tvPMLrc.setText("Unknow");
        app.nextSearchMusic();
        SearchSongInfo currentMusic = app.getSearchCurrentMusic();
        model.loadSongInfo(currentMusic.getSongid(), new SongInfoCallBack() {
            @Override
            public void onSongInfoLoaded(Song song) {
                //播放音乐
                try {
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
                    lrcDowload(song);
                    musicTime();
                    ImageUtil.setPlayImage(app.getApplicationContext(), song.getSonginfo().getPic_premium(), ivPMAlbum, ivPMBackground);
                    tvPMTitle.setText(song.getSonginfo().getTitle());
                    tvPMSinger.setText(song.getSonginfo().getAuthor());
                } catch (NullPointerException e) {
                    Toast toast = Toast.makeText(SearchActivity.this, "没有当前歌曲的资源", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);//居中显示
                    toast.show();
                }
            }
        });
    }
    /*
     **随机播放
     */
    public void randomMusic() {
        tvPMLrc.setText("Unknow");
        app.radomSearchMusic();
        SearchSongInfo currentMusic = app.getSearchCurrentMusic();
        model.loadSongInfo(currentMusic.getSongid(), new SongInfoCallBack() {
            @Override
            public void onSongInfoLoaded(Song song) {
                try {
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
                    lrcDowload(song);
                    musicTime();
                    ImageUtil.setPlayImage(app.getApplicationContext(), song.getSonginfo().getPic_premium(), ivPMAlbum, ivPMBackground);
                    tvPMTitle.setText(song.getSonginfo().getTitle());
                    tvPMSinger.setText(song.getSonginfo().getAuthor());
                } catch (NullPointerException e) {
                    Toast toast = Toast.makeText(SearchActivity.this, "没有当前歌曲的资源", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);//居中显示
                    toast.show();
                }
            }

        });
    }
    /*
     **单曲循环
     */
    public void thisMusic() {
        tvPMLrc.setText("Unknow");
        SearchSongInfo currentMusic = app.getSearchCurrentMusic();
        model.loadSongInfo(currentMusic.getSongid(), new SongInfoCallBack() {
            @Override
            public void onSongInfoLoaded(Song song) {
                try {
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
                    lrcDowload(song);
                    musicTime();
                    ImageUtil.setPlayImage(app.getApplicationContext(), song.getSonginfo().getPic_premium(), ivPMAlbum, ivPMBackground);
                    tvPMTitle.setText(song.getSonginfo().getTitle());
                    tvPMSinger.setText(song.getSonginfo().getAuthor());
                } catch (NullPointerException e) {
                    Toast toast = Toast.makeText(SearchActivity.this, "没有当前歌曲的资源", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);//居中显示
                    toast.show();
                }
            }

        });
    }

    private void setAdapter() {
        adapter = new QueryMusicAdapter(SearchActivity.this, searchSongInfos, lvNewMusic);
        lvNewMusic.setAdapter(adapter);
    }
    private void setViews(){
        lvNewMusic=findViewById(R.id.lv_music);
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
        ivPMStart=findViewById(R.id.ivPMStart);
        tvCMTitle=findViewById(R.id.tvCMTitle);
        tvPMSinger=findViewById(R.id.tvPMSinger);
        ivCMpause=findViewById(R.id.ivCMpause);
        tvCMmodel=findViewById(R.id.tvCMmodel);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListener() {
        //底部按钮播放/暂停
        ivCMpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startOrPause();
            }
        });
        //点击播放
        lvNewMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                app.setSearchSongInfo(searchSongInfos);
                SearchSongInfo searchSongInfo =searchSongInfos.get(position);
                //获取当前点击音乐的song_id
                String song_id = searchSongInfo.getSongid();
                //当点击列表播放时,将点击的音乐的position存入Appliaction
                app.setSearchPosition(position);
                //MusicModel model=new MusicModel();
                model.loadSongInfo(song_id, new SongInfoCallBack() {
                    @Override
                    public void onSongInfoLoaded(Song song) {
                        try {
                            //音乐信息加载完成后异步播放音乐
                            String fileLink = song.getBitrate().getFile_link();
                            //playMusic(fileLink);
                            //音乐开始播放获取当前音乐的基本信息
                            String title = song.getSonginfo().getTitle();
                            String smallPicPath = song.getSonginfo().getPic_small();
                            //获取当前Activity
                            playMusic(fileLink);
                            tvCMTitle.setText(title);
                            ivCMpause.setImageResource(R.mipmap.btn_start);
                            lrcDowload(song);
                            musicTime();
                            //异步加载图片
                            BitmapUtils.loadBitmap(SearchActivity.this, smallPicPath, new BitmapCallback() {
                                @Override
                                public void onBitmapLoaded(Bitmap bitmap) {
                                    if (bitmap != null) {//图片下载成功
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
                            ImageUtil.setPlayImage(SearchActivity.this,song, ivPMAlbum, ivPMBackground,tvPMTitle,tvPMSinger);
                        }catch (NullPointerException e){
                            Toast toast=Toast.makeText(SearchActivity.this,"没有当前歌曲的资源",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);//居中显示
                            toast.show();
                        }
                    }
                });

            }
        });

        //播放模式切换
        tvCMmodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String model;
                model=tvCMmodel.getText().toString();
                switch (model)
                {
                    case "顺序播放":
                        tvCMmodel.setText("随机播放");
                        app.setModel(1);
                        break;
                    case "随机播放":
                        tvCMmodel.setText("单曲循环");
                        app.setModel(2);
                        break;
                    case "单曲循环":
                        tvCMmodel.setText("顺序播放");
                        app.setModel(0);
                        break;
                }

            }
        });

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
        //给llBottomBar添加touch事件  拦截事件,解决点击播放页面背景图片的bug
        tvCMTitle.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                //自己消费touch事件
                return true;
            }
        });
        //给rlPlayMusic添加touch事件  拦截事件,解决点击播放页面背景图片的bug
        rlPlayMusic.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                //自己消费touch事件
                return true;
            }
        });
    }

    public void controllMusic(View view){
        switch (view.getId()){
            case R.id.ivPMStart://播放按钮
                startOrPause();
                break;
            case R.id.ivPMNext://下一首
                if(app.getModel()==0)
                    nextMusic();
                if(app.getModel()==1)
                    //随机播放
                    randomMusic();
                if(app.getModel()==2)
                    //单曲循环
                    thisMusic();
                break;
            case R.id.ivPMPre://上一首
                if(app.getModel()==0)
                    preMusic();
                if(app.getModel()==1)
                    randomMusic();
                //随机播放
                if(app.getModel()==2)
                    //单曲循环
                    thisMusic();
                break;
            default:
        }
    }

    public void playMusic(String url){
        try {
            //重置播放器
            mediaPlayer.reset();
            //设置要播放的资源
            mediaPlayer.setDataSource(url);
            //异步加载音乐信息
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                int duration2 = mediaPlayer.getDuration() / 1000;
                int position = mediaPlayer.getCurrentPosition();
                tvPMCurrentTime.setText(calculateTime(position/1000));
                tvPMTotalTime.setText(calculateTime(duration2));
                seekBar.setMax(duration2*1000);
                seekBar.setProgress(position);
            };

        });
    }

    public void lrcDowload(Song song) {
        m1 = song;
        if(m1.getLrc()!=null){ //以前已经下载过了
            return;
        }
        String lrcPath = m1.getSonginfo().getLrclink();
        Log.e("path",lrcPath);
        if(lrcPath==null || lrcPath.equals("")){
            Toast.makeText(SearchActivity.this, "该歌曲没有歌词", Toast.LENGTH_SHORT).show();
            return;
        }
        model.loadLrc(lrcPath, new LrcCallback(){
            public void onLrcLoaded(HashMap<String, String> lrc) {
                m1.setLrc(lrc);
            }
        });

    }
    /**
     * 暂停或播放
     */
    public void startOrPause() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            ivPMStart.setImageResource(R.mipmap.btn_pause);
            ivCMpause.setImageResource(R.mipmap.btn_pause);

        }else{
            mediaPlayer.start();
            ivPMStart.setImageResource(R.mipmap.btn_start);
            ivCMpause.setImageResource(R.mipmap.btn_start);
        }
    }

    //setSeekBar
    public void setSeekBar() {
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

    /**
     * 当点击后退键时执行
     */
    @Override
    public void onBackPressed() {
        if(rlPlayMusic.getVisibility() == View.VISIBLE){
            //隐藏
            rlPlayMusic.setVisibility(View.INVISIBLE);
            ScaleAnimation anim = new ScaleAnimation(1, 0, 1, 0, 0, rlPlayMusic.getHeight());
            anim.setDuration(300);
            rlPlayMusic.startAnimation(anim);
        }
        else{
            //mediaPlayer.release();
            //System.exit(0);
            mediaPlayer.reset();
            ivPMStart.setImageResource(R.mipmap.btn_pause);
            ivCMpause.setImageResource(R.mipmap.btn_pause);
            startActivity(new Intent(SearchActivity.this,MainActivity.class));
            finish();
            //super.onBackPressed();
            //onDestroy();
            //finish();
        }
    }

    @Override
    public void onDestroy(){
        //把adapter中的线程给停掉
        if(adapter!=null) {
            adapter.stopThread();
        }
        //释放mediaplayer
        //mediaPlayer.release();
        super.onDestroy();
    }
}
