package com.example.music_player.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if(ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    /* Показать объяснение пользователю  асинхронно  - не блокировать
                    //этот поток ожидает ответа пользователя! После пользователя
                     вижу объяснение, попробуйте еще раз запросить разрешения */
                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
                } return false;
            } else { return false; }
        } else { return false; } // Разрешение уже предоставлено
    }
}
