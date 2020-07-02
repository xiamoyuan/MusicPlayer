package cn.tedu.musicplayer.model;

import cn.tedu.musicplayer.entity.Song;

public interface SongInfoCallBack {
    /**
     * 当前音乐信息加载完成后回调该方法
     * @param song
     */
    void onSongInfoLoaded(Song song);
}
