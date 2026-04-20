package com.example.sdcard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText editTextData;
    Button buttonSave, buttonView;
    TextView textViewData;
    MaterialCardView outputCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextData = findViewById(R.id.editTextData);
        buttonSave = findViewById(R.id.buttonSave);
        buttonView = findViewById(R.id.buttonView);
        textViewData = findViewById(R.id.textViewData);
        outputCard = findViewById(R.id.outputCard);

        // SAVE
        buttonSave.setOnClickListener(v -> {
            String data = editTextData.getText().toString();

            try {
                File file = new File(getExternalFilesDir(null), "myfile.txt");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data.getBytes());
                fos.close();

                Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                editTextData.setText("");

            } catch (Exception e) {
                Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show();
            }
        });

        // VIEW (SHOW BELOW)
        buttonView.setOnClickListener(v -> {
            try {
                File file = new File(getExternalFilesDir(null), "myfile.txt");

                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                fis.close();

                String text = new String(data);
                textViewData.setText(text);

                outputCard.setVisibility(View.VISIBLE); // show card

            } catch (Exception e) {
                textViewData.setText("No Data Found");
                outputCard.setVisibility(View.VISIBLE);
            }
        });
    }
}
