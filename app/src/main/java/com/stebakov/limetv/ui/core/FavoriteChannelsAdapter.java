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
import com.stebakov.limetv.data.core.FavoriteChannelsDB;

import java.util.ArrayList;

public class FavoriteChannelsAdapter extends RecyclerView.Adapter<FavoriteChannelsAdapter.MyViewHolder> {

    Context context;
    FavoriteChannelsDB fvrChannelsDB;

    public FavoriteChannelsAdapter(Context ct, FavoriteChannelsDB fvCDB){
        this.context = ct;
        this.fvrChannelsDB = fvCDB;
    }

    @NonNull
    @Override
    public FavoriteChannelsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.raw_channels, parent, false);
        return new FavoriteChannelsAdapter.MyViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull FavoriteChannelsAdapter.MyViewHolder holder, int position) {
        ArrayList<String> name = fvrChannelsDB.getNames();
        ArrayList<String> image = fvrChannelsDB.getImg();
        holder.name.setText(name.get(position));
        Picasso.with(context)
                .load(image.get(position))
                .fit()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.image);
        holder.btnFav.setBackgroundResource(R.drawable.ic_baseline_star_green_24);
        holder.btnFav.setOnClickListener(view -> {
                fvrChannelsDB.upgradeFavorite(name.get(holder.getAdapterPosition()),"0");
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
        return fvrChannelsDB.getNames().size();
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