/**
  * Copyright 2020 bejson.com 
  */
package cn.tedu.musicplayer.entity;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Auto-generated: 2020-07-01 16:56:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SongQueryBean implements Parcelable {

    private List<QuerySong> song;
    private String order;
    private int error_code;
    private List<Album> album;

    protected SongQueryBean(Parcel in) {
        song = in.createTypedArrayList(QuerySong.CREATOR);
        order = in.readString();
        error_code = in.readInt();
        album = in.createTypedArrayList(Album.CREATOR);
    }

    public static final Creator<SongQueryBean> CREATOR = new Creator<SongQueryBean>() {
        @Override
        public SongQueryBean createFromParcel(Parcel in) {
            return new SongQueryBean(in);
        }

        @Override
        public SongQueryBean[] newArray(int size) {
            return new SongQueryBean[size];
        }
    };

    public void setSong(List<QuerySong> song) {
         this.song = song;
     }
     public List<QuerySong> getSong() {
         return song;
     }

    public void setOrder(String order) {
         this.order = order;
     }
     public String getOrder() {
         return order;
     }

    public void setError_code(int error_code) {
         this.error_code = error_code;
     }
     public int getError_code() {
         return error_code;
     }

    public void setAlbum(List<Album> album) {
         this.album = album;
     }
     public List<Album> getAlbum() {
         return album;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(song);
        dest.writeString(order);
        dest.writeInt(error_code);
        dest.writeTypedList(album);
    }
}