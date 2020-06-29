package com.example.music_player.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.music_player.R;
import com.example.music_player.model.Song;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {
    public static final String ARG_ITEM_TITLE = "itemTitle";
    private Song song;
    String itemTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            itemTitle = getArguments().getString(ARG_ITEM_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        TextView textView = view.findViewById(R.id.second);
        textView.setText(itemTitle);

        return view;
    }
}
