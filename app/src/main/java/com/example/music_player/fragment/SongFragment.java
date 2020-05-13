package com.example.music_player.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.music_player.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {
    public static final String ARG_ITEM_ID = "itemId";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);

        return view;
    }
}
