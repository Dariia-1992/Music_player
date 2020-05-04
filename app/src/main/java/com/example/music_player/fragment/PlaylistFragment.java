package com.example.music_player.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.music_player.R;
import com.example.music_player.adapter.SongAdapter;
import com.example.music_player.model.Song;

import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * A simple {@link Fragment} subclass.
 */
@RuntimePermissions
public class PlaylistFragment extends Fragment {
    private static final int PERMISSION_REQUEST = 101;
    private View view;
    List<Song> tempSongList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_playlist, container, false);
        Context context = view.getContext();
        SongAdapter adapter = new SongAdapter(tempSongList, listener);
        RecyclerView recyclerView = view.findViewById(R.id.playlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button start = view.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllAudioFromDevice(context);
            }
        });
        return view;
    }
    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void getAllAudioFromDevice(final Context context) {
        if(EasyPermissions.hasPermissions(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.Audio.Media.TITLE};
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

            if(cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    tempSongList.add(new Song(title));
                }
            }
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_write_permission), PERMISSION_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private final SongAdapter.OnClickItem listener = id -> {
        View view = getView();
        if (view == null)
            return;
        Bundle bundle = new Bundle();
        bundle.putString(SongFragment.ARG_ITEM_ID, id);
        Navigation.findNavController(getView()).navigate(R.id.action_playlistFragment_to_songFragment, bundle);
    };
}
