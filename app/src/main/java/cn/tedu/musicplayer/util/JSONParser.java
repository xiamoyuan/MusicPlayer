package cn.tedu.musicplayer.util;


import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.tedu.musicplayer.entity.Album;
import cn.tedu.musicplayer.entity.JsonRootBean;
import cn.tedu.musicplayer.entity.QuerySong;
import cn.tedu.musicplayer.entity.SearchSongInfo;
import cn.tedu.musicplayer.entity.SongQueryBean;
import cn.tedu.musicplayer.entity.Song_list;
import cn.tedu.musicplayer.entity.Song;
public class JSONParser {
    public static List<Song_list> parseMusicList(String json) throws Exception{
        Gson gson = new Gson();
        JsonRootBean jsonRootBean=gson.fromJson(json,JsonRootBean.class);
        List<Song_list> song_lists=jsonRootBean.getSong_list();
        return song_lists;
    }
    public static Song parseMusicInfo(String data)throws Exception{
        JSONObject obj=new JSONObject(data);
        Gson gson=new Gson();
        Song song=gson.fromJson(data, Song.class);
        return song;
    }
    public static List<SearchSongInfo> parseMusicQuery(String data)throws Exception{
        Gson gson=new Gson();
        SongQueryBean songQueryBean=gson.fromJson(data, SongQueryBean.class);
        List<SearchSongInfo> searchSongInfos = new ArrayList<>();
        List<Album> albums = songQueryBean.getAlbum();
        List<QuerySong> querySongList = songQueryBean.getSong();
        for(int i=0;i<songQueryBean.getSong().size();i++){
            searchSongInfos.add(new SearchSongInfo(albums.get(i).getArtistpic(),
                    querySongList.get(i).getSongname(),querySongList.get(i).getSongid(),querySongList.get(i).getArtistname()));
        }
        return searchSongInfos;
    }
}
