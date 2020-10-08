package com.example.safetogether.crew;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safetogether.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void flash() throws CameraAccessException {
        final CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        final String cameraId;
        cameraId = cameraManager.getCameraIdList()[0];
        final Timer timer = new Timer();
        final Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        class Flash extends TimerTask {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void run() {
                try {
                    if ( true ) { //todo:someone is in the way
                        timer.cancel();
                    }
                    else {
                        cameraManager.setTorchMode(cameraId, true);
                        cameraManager.setTorchMode(cameraId, false);
                        vibrate.vibrate(100);
                    }
                } catch (
                        CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        timer.schedule(new Flash(), 0, 1000);
    }
}
