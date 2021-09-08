package com.zhuelise.lab03sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button topRight;
    TextView topLeft;
    Button bottomLeft;
    TextView bottomRight;
    View.OnClickListener listen;
    String current;
    int topRightCount, topLeftCount, bottomRightCount, bottomLeftCount = 0;
    TextView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topRight = (Button) (findViewById(R.id.top_right_button));
        topLeft = (TextView) (findViewById(R.id.top_left_textview));
        bottomLeft = (Button) (findViewById(R.id.bottom_left_button));
        bottomRight = (TextView) (findViewById(R.id.bottom_right_textview));
        topRight.setOnClickListener(listen);
        topLeft.setOnClickListener(listen);
        bottomRight.setOnClickListener(listen);
        bottomLeft.setOnClickListener(listen);

        listen = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v = (TextView)(view);
                int count = Integer.parseInt(""+v.getText());
                current = getString(R.string.display_count, count);
                v.setText(current);
            }
        };

    }
}