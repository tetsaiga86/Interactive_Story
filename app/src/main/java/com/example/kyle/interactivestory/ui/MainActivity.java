package com.example.kyle.interactivestory.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kyle.interactivestory.R;

public class MainActivity extends AppCompatActivity {

    private EditText mNameField;
    private Button mStartButton;
    private ImageButton mSettingsButton;
    private Boolean mKeepNameSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameField = (EditText)findViewById(R.id.nameEditText);
        mStartButton = (Button)findViewById(R.id.startButton);
        mSettingsButton = (ImageButton)findViewById(R.id.settingsButton);

        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSettings();
            }
        });

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameField.getText().toString();
                startStory(name);
            }
        });
        TextView tx = (TextView)findViewById(R.id.settingsText);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Alien.ttf");

        tx.setTypeface(custom_font);
        settingsCheck();
    }

    private void settingsCheck() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mKeepNameSwitch = sharedPreferences.getBoolean("KEEP_NAME_SWITCH", true);
    }

    private void startSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    private void startStory(String name) {
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra(getString(R.string.key_name), name);

        startActivity(intent);
    }



    @Override
    protected void onResume() {
        settingsCheck();
        super.onResume();
        if(!mKeepNameSwitch) {
            mNameField.setText("");
        }
    }
}
