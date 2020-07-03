package cn.tedu.musicplayer.entity;

import android.os.Parcel;
import android.os.Parcelable;


public class SearchSongInfo implements Parcelable {
    private String artistpic;
    private String songname;
    private String songid;
    private String artistname;


    public SearchSongInfo(String artistpic, String songname, String songid, String artistname) {
        this.artistpic = artistpic;
        this.songname = songname;
        this.songid = songid;
        this.artistname = artistname;
    }

    protected SearchSongInfo(Parcel in) {
        artistpic = in.readString();
        songname = in.readString();
        songid = in.readString();
        artistname = in.readString();
    }

    public static final Creator<SearchSongInfo> CREATOR = new Creator<SearchSongInfo>() {
        @Override
        public SearchSongInfo createFromParcel(Parcel in) {
            return new SearchSongInfo(in);
        }

        @Override
        public SearchSongInfo[] newArray(int size) {
            return new SearchSongInfo[size];
        }
    };

    public String getArtistpic() {
        return artistpic;
    }

    public void setArtistpic(String artistpic) {
        this.artistpic = artistpic;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(artistpic);
        dest.writeString(songname);
        dest.writeString(songid);
        dest.writeString(artistname);
    }
}
