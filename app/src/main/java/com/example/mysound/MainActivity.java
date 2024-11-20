package com.example.mysound;

import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SoundPool sp;
    private int soundId;
    private boolean spLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new SoundPool.Builder()
                .setMaxStreams(10)
                .build();

        sp.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
            if (status == 0) {
                spLoaded = true;
            } else {
                // Show error toast if loading fails
                Toast.makeText(MainActivity.this, "Gagal load", Toast.LENGTH_SHORT).show();
            }
        });

        soundId = sp.load(this, R.raw.clinking_glasses, 1);

        Button btnSound = findViewById(R.id.btn_sound_pool);

        btnSound.setOnClickListener(v -> {
            if (spLoaded) {
                // Play the sound if it's loaded
                sp.play(soundId, 1f, 1f, 0, 0, 1f);
            }
        });
    }
}