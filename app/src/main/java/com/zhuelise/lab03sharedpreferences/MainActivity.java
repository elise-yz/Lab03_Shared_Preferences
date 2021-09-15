package com.zhuelise.lab03sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button topRight;
    TextView topLeft;
    Button bottomLeft;
    TextView bottomRight;
    ConstraintLayout layout;
    String current;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView v;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topRight = findViewById(R.id.top_right_button);
        topLeft = (findViewById(R.id.top_left_textview));
        bottomLeft = (findViewById(R.id.bottom_left_button));
        bottomRight = (findViewById(R.id.bottom_right_textview));
        seekBar = (findViewById(R.id.seek_bar));
        layout = findViewById(R.id.constraint_layout);
        preferences = getSharedPreferences("com.zhuelise.lab03.sharedpreferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
        topRight.setOnClickListener(this);
        topLeft.setOnClickListener(this);
        bottomRight.setOnClickListener(this);
        bottomLeft.setOnClickListener(this);
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editor.clear();
                setInitialValues();
                editor.apply();
                return false;
            }
        });
        setInitialValues();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                topRight.setTextSize(i);
                topLeft.setTextSize(i);
                bottomRight.setTextSize(i);
                bottomLeft.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Snackbar snackbar = Snackbar.make(layout, getString(R.string.font_size, seekBar.getProgress()), Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
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
        setInitialValues();
    }
    private void setInitialValues(){
        topLeft.setText(preferences.getString("topLeft", "0"));
        topRight.setText(preferences.getString("topRight", "0"));
        bottomLeft.setText(preferences.getString("bottomLeft", "0"));
        bottomRight.setText(preferences.getString("bottomRight", "0"));
        seekBar.setProgress(preferences.getInt("seekBar", 25));
    }
    @Override
    protected void onPause() {
        super.onPause();
        editor.putString("topLeft", (topLeft.getText().toString()));
        editor.putString("topRight", (topRight.getText().toString()));
        editor.putString("bottomLeft", bottomLeft.getText().toString());
        editor.putString("bottomRight", bottomRight.getText().toString());
        editor.putInt("seekBar", seekBar.getProgress());
        editor.apply();
    }
}