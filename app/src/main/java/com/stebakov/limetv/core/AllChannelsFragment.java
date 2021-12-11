package com.stebakov.limetv.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stebakov.limetv.R;
import com.stebakov.limetv.data.core.Channel;
import com.stebakov.limetv.data.core.Channels;
import com.stebakov.limetv.data.core.NetWorkService;
import com.stebakov.limetv.ui.core.AllChannelsAdapter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllChannelsFragment extends Fragment {

    RecyclerView rvChannels;
    AllChannelsAdapter allChannelsAdapter;

    public AllChannelsFragment(){

    }

    public static AllChannelsFragment newInstance(){
        return new AllChannelsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.all_channels_fragment, container, false);
        rvChannels = root.findViewById(R.id.rv_all_channels);
        getChannelsApi();
        return root;
    }

    private void getChannelsApi(){
        NetWorkService.getInstance()
                .getJSONApi()
                .getChannels()
                .enqueue(new Callback<Channels>() {
                    @Override
                    public void onResponse(@NonNull Call<Channels> call, @NonNull Response<Channels> response) {
                        if (response.isSuccessful()){
                            assert response.body() != null;
                            ArrayList<Channel> channels = response.body().getChannels();
                            allChannelsAdapter = new AllChannelsAdapter(getContext(),channels);
                            rvChannels.setLayoutManager(new LinearLayoutManager(getContext()));
                            rvChannels.setAdapter(allChannelsAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Channels> call, Throwable t) {

                    }
                });
    }

}
