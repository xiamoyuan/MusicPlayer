package cn.tedu.musicplayer.app;

import android.app.Application;

import java.util.List;

import cn.tedu.musicplayer.entity.Song_list;

public class MyApp extends Application {
     private  static MyApp app;
     private List<Song_list>songs;
     private  int position;
     @Override
    public void onCreate()
     {
         super.onCreate();
         app=this;
     }
     public static MyApp getApp()
     { return app;}
    public List<Song_list> getSongs() {
        return songs;
    }

    public void setSongs(List<Song_list> songs) {
        this.songs = songs;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    /**
     * 获取当前音乐
     */
    public Song_list getCurrentMusic()
    {
        return songs.get(position);
    }
    /**
     * 上一首
     */
    public void preMusic()
    {
        position=position==0?songs.size()-1:position-1;

    }
    /**
     * 下一首
     */
    public void nextMusic()
    {
        position=position==songs.size()-1?0:position+1;
    }
}
