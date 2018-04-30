package com.example.kazkuro.android_test;

import android.app.AlertDialog;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView    testTextView;
    private Button      testButton;

    private Intent              intent;
    private SpeechRecognizer    recognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeSpeechRegonizer();

        testTextView    = (TextView)findViewById(R.id.testTextViewUI);
        testButton      = (Button)  findViewById(R.id.testButtonUI);

        System.out.println("initializing");


        testTextView.setText("Hello,world!!!!");
        testButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                testTextView.setText(testTextView.getText() + "!");
                recognizer.startListening(intent);
            }
        });
    }

    private void apperResultPopup(List<String> results){
        Toast.makeText(this, results.get(0), Toast.LENGTH_LONG).show();
    }

    private void initializeSpeechRegonizer(){
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());

        recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        recognizer.setRecognitionListener(new RecognitionListener(){

            @Override
            public void onReadyForSpeech(Bundle bundle) {
                System.out.println("start recognize");
            }

            @Override
            public void onBeginningOfSpeech() {
                System.out.println("begin speech");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] aByte) {

            }

            @Override
            public void onEndOfSpeech() {
                System.out.println("end speech");
            }

            @Override
            public void onError(int i) {
                System.out.println("error:" + i);
            }

            @Override
            public void onResults(Bundle bundle) {
                System.out.println("setting results...");
                List<String> recData = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                apperResultPopup(recData);
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

    }
}
