package cn.tedu.musicplayer.fragment;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.tedu.musicplayer.R;
import cn.tedu.musicplayer.activity.MainActivity;
import cn.tedu.musicplayer.adapter.MusicAdapter;
import cn.tedu.musicplayer.app.MyApp;
import cn.tedu.musicplayer.entity.Song_list;
import cn.tedu.musicplayer.model.BitmapCallback;
import cn.tedu.musicplayer.model.MusicListCallback;
import cn.tedu.musicplayer.model.MusicModel;
import cn.tedu.musicplayer.entity.Song;
import cn.tedu.musicplayer.model.SongInfoCallBack;
import cn.tedu.musicplayer.util.BitmapUtils;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class NewMusicListFragment extends Fragment {
    private ListView lvNewMusic;
    private MusicAdapter adapter;
    private MusicModel model;
    private List<Song_list> song_lists;
    private MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_new_music_list_fragment,null);
        setViews(view);
        final MyApp app=MyApp.getApp();
        //调用业务层，加载所有新歌榜音乐
        model=new MusicModel();
        model.loadMusicList(0, 20, new MusicListCallback() {
            @Override
            public void onMusicListLoaded(List<Song_list> song_lists) {
                //拿到网络上的json数据
                NewMusicListFragment.this.song_lists=song_lists;
                //当列表加载完之后存入application
                app.setSongs(song_lists);
                setAdapter();
            }
        });
        setListener();
        musicTime();
        return view;
    }
    private void setAdapter() {
        adapter=new MusicAdapter(getActivity(),song_lists,lvNewMusic);
        lvNewMusic.setAdapter(adapter);
    }
    private void setViews(View view){
        lvNewMusic=view.findViewById(R.id.lv_music);
    }

    @Override
    public void onDestroy(){
        final MainActivity activity=(MainActivity) getActivity();
        activity.mediaPlayer.release();
        super.onDestroy();
        //把adapter中的线程停掉
        adapter.stopThread();

    }
    //
    public void musicTime()
    {
        final MainActivity activity=(MainActivity) getActivity();
        Timer timer = new Timer();
        {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(activity.mediaPlayer!=null&&activity.mediaPlayer.isPlaying()){
                        activity.runOnUiThread(new Runnable(){

                            @Override
                            public void run() {
                                //更新UI
                                int position = activity.mediaPlayer.getCurrentPosition();
                                activity.tvPMCurrentTime.setText(calculateTime(position/1000));
                                activity.seekBar.setProgress(position);

                            }

                        });
                    }
                }
            },0,50);

        }
    }
    public void playMusic(String url)
    {
        final MainActivity activity=(MainActivity) getActivity();
        try {
            activity.mediaPlayer.reset();
            activity.mediaPlayer.setDataSource(url);
            activity.mediaPlayer.prepareAsync();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        activity.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                activity.mediaPlayer.start();
                int duration2 = activity.mediaPlayer.getDuration() / 1000;
                int position = activity.mediaPlayer.getCurrentPosition();
                activity.tvPMCurrentTime.setText(calculateTime(position/1000));
                activity.tvPMTotalTime.setText(calculateTime(duration2));
                activity.seekBar.setMax(duration2*1000);
                activity.seekBar.setProgress(position);


            };

        });

    }


    //计算播放时间
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


    private void setListener() {
        lvNewMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song_list song_list = song_lists.get(position);
                //获取当前点击音乐的song_id
                MyApp app=MyApp.getApp();
                app.setPosition(position);
                String song_id = song_list.getSong_id();
                Log.e("ss",song_id);
                model.loadSongInfo(song_id, new SongInfoCallBack() {
                    @Override
                    public void onSongInfoLoaded(Song song) {
                        String fileLink=song.getBitrate().getFile_link();
                        Log.e("url",fileLink);
                        playMusic(fileLink);
                        String titile=song.getSonginfo().getTitle();
                        String smallPicPath=song.getSonginfo().getPic_small();
                        final MainActivity activity=(MainActivity) getActivity();
                        activity.tvCMTitle.setText(titile);
                        BitmapUtils.loadBitmap(getContext(),smallPicPath, new BitmapCallback(){
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap) {
                            if(bitmap!=null){
                                activity.ivCMPic.setImageBitmap(bitmap);
                                //让imageView转起来
                                RotateAnimation anim = new RotateAnimation(0, 360, activity.ivCMPic.getWidth()/2, activity.ivCMPic.getHeight()/2);
                                anim.setDuration(10000);
                                //匀速旋转
                                anim.setInterpolator(new LinearInterpolator());
                                //无限重复
                                anim.setRepeatCount(Animation.INFINITE);
                                activity.ivCMPic.startAnimation(anim);
                            }

                            else{
                                activity.ivCMPic.setImageResource(R.mipmap.ic_launcher);
                            }
                        }

                    });
                        //更新播放界面中的ivPMAlbum
                        final ImageView ivPMAlbum=activity.ivPMAlbum;
                        final ImageView ivPMBackground=activity.ivPMBackground;
                        final TextView  tvPMTitle=activity.tvPMTitle;
                        final TextView  tvPMSinger=activity.tvPMSinger;
                        String albumPic=song.getSonginfo().getPic_premium();
                        tvPMTitle.setText(titile);
                        tvPMSinger.setText(song.getSonginfo().getAuthor());
                        BitmapUtils.loadBitmap(getContext(),albumPic, new BitmapCallback() {
                            public void onBitmapLoaded(Bitmap bitmap) {
                                if(bitmap!=null){ //下载
                                    ivPMAlbum.setImageBitmap(bitmap);
                                }else{
                                    ivPMAlbum.setImageResource(R.mipmap.default_music_pic);
                                }
                            }
                        });
                        //更新背景图片 ivPMBackground
                        String backPic = song.getSonginfo().getPic_premium();

                        BitmapUtils.loadBitmap(getActivity(),backPic, 8, new BitmapCallback() {
                            public void onBitmapLoaded(Bitmap bitmap) {
                                if(bitmap!=null){ //背景图片下载成功
                                    //把背景图片模糊化处理
                                    BitmapUtils.loadBlurBitmap(bitmap, 10, new BitmapCallback() {
                                        public void onBitmapLoaded(Bitmap bitmap) {
                                            ivPMBackground.setImageBitmap(bitmap);
                                        }
                                    });
                                }else{
                                    ivPMBackground.setImageResource(R.mipmap.default_music_background);
                                }
                            }
                        });
                    }

                });
            }
        });


    }
}