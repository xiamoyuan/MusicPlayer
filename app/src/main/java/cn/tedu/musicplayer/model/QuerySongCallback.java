package cn.tedu.musicplayer.model;


import cn.tedu.musicplayer.entity.SearchSongInfo;

import java.util.List;

public interface QuerySongCallback {
    void QuerySongLoad(List<SearchSongInfo> searchSongInfos);
}
