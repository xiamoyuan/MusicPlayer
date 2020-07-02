package cn.tedu.musicplayer.model;

import cn.tedu.musicplayer.entity.Song_list;

import java.util.List;

public interface MusicListCallback {
    /**
     * 音乐加载完成后回调该方法
     */
    void onMusicListLoaded(List<Song_list> song_lists);
}
