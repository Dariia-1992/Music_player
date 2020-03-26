package com.example.music_player.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.music_player.R;
import com.example.music_player.model.Song;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment {
    private static final int PERMISSION_REQUEST = 1;
    private List<Song> songs;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_playlist, container, false);

        return view;
    }
}
