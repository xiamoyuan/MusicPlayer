package cn.tedu.musicplayer.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import cn.tedu.musicplayer.R;
import cn.tedu.musicplayer.entity.SearchSongInfo;
import cn.tedu.musicplayer.util.ImageLoader;

public class QueryMusicAdapter extends BaseAdapter{
    private Context context;
    private List<SearchSongInfo> musics;
    private ImageLoader imageLoader;
    private LayoutInflater inflater;

    public QueryMusicAdapter(Context context, List<SearchSongInfo> musics,ListView listView) {
        this.context=context;
        this.musics = musics;
        this.imageLoader=new ImageLoader(context,listView);
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        if(musics!=null) {
            return musics.size();
        }
        return 0;
    }

    @Override
    public SearchSongInfo getItem(int position) {
        if(musics!=null) {
            return musics.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lv_music, null);
            holder = new ViewHolder();
            holder.ivAlbum = convertView.findViewById(R.id.ivAlbum);
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            holder.tvSinger =  convertView.findViewById(R.id.tvSinger);
            convertView.setTag(holder);
        }
        holder=(ViewHolder) convertView.getTag();
        //控件的赋值
        SearchSongInfo m = getItem(position);
        holder.tvTitle.setText(m.getSongname());
        holder.tvSinger.setText(m.getArtistname());
        //使用ImageLoader工具类加载网络图片
        imageLoader.displayImage(holder.ivAlbum,m.getArtistpic());
        return convertView;
    }


    class ViewHolder{
        ImageView ivAlbum;
        TextView tvTitle;
        TextView tvSinger;
    }

    /**
     * 停止工作线程
     */
    public void stopThread() {
        imageLoader.stopThread();
    }

}
