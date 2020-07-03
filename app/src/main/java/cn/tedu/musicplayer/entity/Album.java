/**
  * Copyright 2020 bejson.com 
  */
package cn.tedu.musicplayer.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2020-07-01 16:56:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Album implements Parcelable {

    private String albumname;
    private String weight;
    private String artistname;
    private String resource_type_ext;
    private String artistpic;
    private String albumid;

    protected Album(Parcel in) {
        albumname = in.readString();
        weight = in.readString();
        artistname = in.readString();
        resource_type_ext = in.readString();
        artistpic = in.readString();
        albumid = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public void setAlbumname(String albumname) {
         this.albumname = albumname;
     }
     public String getAlbumname() {
         return albumname;
     }

    public void setWeight(String weight) {
         this.weight = weight;
     }
     public String getWeight() {
         return weight;
     }

    public void setArtistname(String artistname) {
         this.artistname = artistname;
     }
     public String getArtistname() {
         return artistname;
     }

    public void setResource_type_ext(String resource_type_ext) {
         this.resource_type_ext = resource_type_ext;
     }
     public String getResource_type_ext() {
         return resource_type_ext;
     }

    public void setArtistpic(String artistpic) {
         this.artistpic = artistpic;
     }
     public String getArtistpic() {
         return artistpic;
     }

    public void setAlbumid(String albumid) {
         this.albumid = albumid;
     }
     public String getAlbumid() {
         return albumid;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(albumname);
        dest.writeString(weight);
        dest.writeString(artistname);
        dest.writeString(resource_type_ext);
        dest.writeString(artistpic);
        dest.writeString(albumid);
    }
}