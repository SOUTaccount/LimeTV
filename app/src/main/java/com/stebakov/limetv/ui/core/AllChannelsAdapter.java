package com.stebakov.limetv.ui.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.stebakov.limetv.R;
import com.stebakov.limetv.data.core.Channel;
import com.stebakov.limetv.data.core.FavoriteChannelsDB;
import java.util.ArrayList;

public class AllChannelsAdapter extends RecyclerView.Adapter<AllChannelsAdapter.MyViewHolder> {
    ArrayList<Channel> channels;
    Context context;
    FavoriteChannelsDB fvrChannelsDB;

    public AllChannelsAdapter(Context ct, ArrayList<Channel> alp){
        this.context = ct;
        this.channels = alp;
    }

    @NonNull
    @Override
    public AllChannelsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.raw_channels, parent, false);
        fvrChannelsDB = new FavoriteChannelsDB(context);
        if (fvrChannelsDB.checkTable() == 0) {
            for (int i = 0; i < channels.size(); i++) {
                fvrChannelsDB.insertData(channels.get(i).getNameRu(),channels.get(i).getImage(),"0");
            }
        }
        return new MyViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull AllChannelsAdapter.MyViewHolder holder, int position) {
        holder.name.setText(channels.get(position).getNameRu());
        if (channels.get(position).getCurrent()!= null){
            holder.title.setText(channels.get(position).getCurrent().getTitle());
        }
        Picasso.with(context)
                .load(channels.get(position).getImage())
                .fit()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.image);
        if (fvrChannelsDB.getFvr(channels.get(holder.getAdapterPosition()).getNameRu()).equals("1")){
            holder.btnFav.setBackgroundResource(R.drawable.ic_baseline_star_green_24);
        } else if (fvrChannelsDB.getFvr(channels.get(holder.getAdapterPosition()).getNameRu()).equals("0")){
            holder.btnFav.setBackgroundResource(R.drawable.ic_baseline_star_shadow_24);
        }
        holder.btnFav.setOnClickListener(view -> {
            if (fvrChannelsDB.getFvr(channels.get(holder.getAdapterPosition()).getNameRu()).equals("0")){
                fvrChannelsDB.upgradeFavorite(channels.get(holder.getAdapterPosition()).getNameRu(),"1");
                holder.btnFav.setBackgroundResource(R.drawable.ic_baseline_star_green_24);
            } else if (fvrChannelsDB.getFvr(channels.get(holder.getAdapterPosition()).getNameRu()).equals("1")){
                fvrChannelsDB.upgradeFavorite(channels.get(holder.getAdapterPosition()).getNameRu(),"0");
                holder.btnFav.setBackgroundResource(R.drawable.ic_baseline_star_shadow_24);
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView title;
        Button btnFav;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_channel);
            name = itemView.findViewById(R.id.tv_channel_name);
            title = itemView.findViewById(R.id.tv_channel_title);
            btnFav = itemView.findViewById(R.id.btn_fvr);
        }
    }

}