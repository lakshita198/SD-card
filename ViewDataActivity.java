package com.example.sdcard;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;

public class ViewDataActivity extends AppCompatActivity {

    TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        textViewData = findViewById(R.id.textViewData);

        try {
            File file = new File(getExternalFilesDir(null), "myfile.txt");

            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String text = new String(data);
            textViewData.setText(text);

        } catch (Exception e) {
            textViewData.setText("No Data Found");
        }
    }
}
