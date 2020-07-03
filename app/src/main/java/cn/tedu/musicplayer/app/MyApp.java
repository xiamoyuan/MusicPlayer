package cn.tedu.musicplayer.app;

import android.app.Application;

import java.util.List;

import cn.tedu.musicplayer.entity.SearchSongInfo;
import cn.tedu.musicplayer.entity.Song;
import cn.tedu.musicplayer.entity.Song_list;

public class MyApp extends Application {
     private  static MyApp app;
     private List<Song_list>songs;
     private List<SearchSongInfo> searchSongInfos;
     private int position; // 当前正在播放音乐的位置
     private int searchPosition; // 当前正在播放音乐的位置
     private Song searchSong;

     public Song getSearchSong(){
         return searchSong;
     }

    public void setSearchSong(Song searchSong){
        this.searchSong=searchSong;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    private int model;
     @Override
    public void onCreate() {
         super.onCreate();
         app=this;
     }
     public static MyApp getApp()
     { return app;}
    public List<Song_list> getSongs() {
        return songs;
    }

    public List<SearchSongInfo> getSearchSongs() {
        return searchSongInfos;
    }

    public void setSongs(List<Song_list> songs) {
        this.songs = songs;
    }

    public void setSearchSongInfo(List<SearchSongInfo> searchSongInfos) {
        this.searchSongInfos = searchSongInfos;
    }
    /*public int getPosition() {
        return position;
    }*/

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

    public Song getSingleCurrentMusic() {
        return searchSong;
    }

    public void setSearchPosition(int searchPosition) {
        this.searchPosition = searchPosition;
    }


    /**
     * 获取当前正在播放的音乐
     *
     * @return
     */
    public SearchSongInfo getSearchCurrentMusic() {
        return searchSongInfos.get(searchPosition);
    }

    /**
     * 上一首
     */
    public void preMusic() {
        position=position==0?songs.size()-1:position-1;

    }
    /**
     * 下一首
     */
    public void nextMusic()
    {
        position=position==songs.size()-1?0:position+1;
    }
    /**
     * 随机播放
     */
    public void radomMusic()
    {
        position=(int)(Math.random()*(songs.size()-1));
    }

    /**
     * 换到上一首歌曲
     */
    public void preSearchMusic() {
        searchPosition = searchPosition == 0 ? searchSongInfos.size()-1 : searchPosition-1;
    }

    /**
     * 换到下一首歌曲
     */
    public void nextSearchMusic() {
        searchPosition = searchPosition == searchSongInfos.size()-1 ? 0 : searchPosition+1;
    }

    /**
     * 随机播放
     */
    public void radomSearchMusic()
    {
        searchPosition=(int)(Math.random()*(searchSongInfos.size()-1));
    }
}
