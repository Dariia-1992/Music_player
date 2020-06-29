package com.example.music_player.fragment;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.music_player.R;
import com.example.music_player.adapter.SampleFragmentPagerAdapter;
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
    public static final String ARG_PAGE = "ARG_PAGE";
    private SampleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private View view;
    List<Song> tempSongList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_playlist, container, false);
        Context context = view.getContext();

        getAllAudioFromDevice(context);

        RecyclerView recyclerView = view.findViewById(R.id.playlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SongAdapter adapter = new SongAdapter(tempSongList, listener);
        recyclerView.setAdapter(adapter);

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

    public static PlaylistFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PlaylistFragment fragment = new PlaylistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private final SongAdapter.OnClickItem listener = title -> {
        View view = getView();
        if (view == null)
            return;
        Bundle bundle = new Bundle();
        bundle.putString(SongFragment.ARG_ITEM_TITLE, title);
        Navigation.findNavController(getView()).navigate(R.id.action_playlistFragment_to_songFragment, bundle);
    };
}
