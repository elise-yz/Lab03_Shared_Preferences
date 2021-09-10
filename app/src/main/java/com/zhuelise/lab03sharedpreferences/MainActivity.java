package com.zhuelise.lab03sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button topRight;
    TextView topLeft;
    Button bottomLeft;
    TextView bottomRight;
    View.OnClickListener listen;
    String current;
    //int topRightCount, topLeftCount, bottomRightCount, bottomLeftCount = 0;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView v;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topRight = (Button) (findViewById(R.id.top_right_button));
        topLeft = (TextView) (findViewById(R.id.top_left_textview));
        bottomLeft = (Button) (findViewById(R.id.bottom_left_button));
        bottomRight = (TextView) (findViewById(R.id.bottom_right_textview));
        seekBar = (SeekBar) (findViewById(R.id.seek_bar));
        preferences = getSharedPreferences("com.zhuelise.lab03.sharedpreferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
        topRight.setOnClickListener(this);
        topLeft.setOnClickListener(this);
        bottomRight.setOnClickListener(this);
        bottomLeft.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        v = (TextView) (view);
        int count = Integer.parseInt(v.getText().toString()) + 1;
        current = getString(R.string.display_count, count);
        v.setText(current);
    }

    @Override
    protected void onResume() {
        super.onResume();
        topLeft.setText(preferences.getString("topLeft", "0"));
        topRight.setText(preferences.getString("topRight", "0"));
        bottomLeft.setText(preferences.getString("bottomLeft", "0"));
        bottomRight.setText(preferences.getString("bottomRight", "0"));
        seekBar.setProgress(preferences.getInt("seekBar", 0));
    }

    @Override
    protected void onPause() {
        super.onPause();
        editor.putString("topLeft", topLeft.getText().toString());
        editor.putString("topRight", topRight.getText().toString());
        editor.putString("bottomLeft", bottomLeft.getText().toString());
        editor.putString("bottomRight", bottomRight.getText().toString());
        editor.putInt("seekBar", seekBar.getProgress());
        editor.apply();
    }
}