package com.example.botspeech;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    private SpeechRecognizer speechRecognizer;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.US);
            } else {
                Toast.makeText(this, "Failed to initialize TextToSpeech", Toast.LENGTH_SHORT).show();
            }
        });

        // Check and request RECORD_AUDIO permission
        ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                startListening();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
        } else {
            startListening();
        }
    }

    private void startListening() {
        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {}

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                switch (error) {
                    case SpeechRecognizer.ERROR_AUDIO:
                        Toast.makeText(MainActivity.this, "Audio recording error", Toast.LENGTH_SHORT).show();
                        break;
                    case SpeechRecognizer.ERROR_CLIENT:
                        Toast.makeText(MainActivity.this, "Client error", Toast.LENGTH_SHORT).show();
                        break;
                    case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                        Toast.makeText(MainActivity.this, "Insufficient permissions", Toast.LENGTH_SHORT).show();
                        break;
                    case SpeechRecognizer.ERROR_NETWORK:
                        Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                        break;
                    case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                        Toast.makeText(MainActivity.this, "Network timeout", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onResults(Bundle results) {
                // Handle recognition results here
            }

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release resources
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }
}
