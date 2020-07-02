/**
  * Copyright 2020 bejson.com 
  */
package cn.tedu.musicplayer.entity;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * Auto-generated: 2020-06-29 9:5:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean implements Parcelable {

    private List<Song_list> song_list;
    private Billboard billboard;
    private int error_code;

    protected JsonRootBean(Parcel in) {
        song_list = in.createTypedArrayList(Song_list.CREATOR);
        billboard = in.readParcelable(Billboard.class.getClassLoader());
        error_code = in.readInt();
    }

    public static final Creator<JsonRootBean> CREATOR = new Creator<JsonRootBean>() {
        @Override
        public JsonRootBean createFromParcel(Parcel in) {
            return new JsonRootBean(in);
        }

        @Override
        public JsonRootBean[] newArray(int size) {
            return new JsonRootBean[size];
        }
    };

    public void setSong_list(List<Song_list> song_list) {
         this.song_list = song_list;
     }
     public List<Song_list> getSong_list() {
         return song_list;
     }

    public void setBillboard(Billboard billboard) {
         this.billboard = billboard;
     }
     public Billboard getBillboard() {
         return billboard;
     }

    public void setError_code(int error_code) {
         this.error_code = error_code;
     }
     public int getError_code() {
         return error_code;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(song_list);
        dest.writeParcelable(billboard, flags);
        dest.writeInt(error_code);
    }
}