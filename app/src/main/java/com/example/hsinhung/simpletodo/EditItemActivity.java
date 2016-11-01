package com.example.hsinhung.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    private EditText etNewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        String content = getIntent().getExtras().getString("content", "");
        etNewContent = (EditText) findViewById(R.id.etNewContent);
        etNewContent.setText(content);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("position", getIntent().getExtras().getInt("position", -1));
                intent.putExtra("content", etNewContent.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
