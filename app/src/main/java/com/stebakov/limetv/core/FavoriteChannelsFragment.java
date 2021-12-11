package com.stebakov.limetv.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stebakov.limetv.R;
import com.stebakov.limetv.data.core.FavoriteChannelsDB;
import com.stebakov.limetv.ui.core.FavoriteChannelsAdapter;

public class FavoriteChannelsFragment extends Fragment {

    RecyclerView rvChannels;
    FavoriteChannelsDB favoriteChannelsDB;
    FavoriteChannelsAdapter favoriteChannelsAdapter;

    public FavoriteChannelsFragment(){

    }

    public static FavoriteChannelsFragment newInstance(){
        return new FavoriteChannelsFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.favorite_channels_fragment, container, false);
        rvChannels = root.findViewById(R.id.rv_favorite_channels);
        favoriteChannelsDB = new FavoriteChannelsDB(getContext());
        favoriteChannelsAdapter = new FavoriteChannelsAdapter(getContext(),favoriteChannelsDB);
        rvChannels.setLayoutManager(new LinearLayoutManager(getContext()));
        rvChannels.setAdapter(favoriteChannelsAdapter);
        return root;
    }

}
