package cn.tedu.musicplayer.util;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import cn.tedu.musicplayer.entity.JsonRootBean;
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
}
