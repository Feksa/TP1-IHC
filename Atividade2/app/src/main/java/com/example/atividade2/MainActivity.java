package com.example.atividade2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private EditText et;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.editTextText);

        bt = (Button) findViewById(R.id.button);
    }

    public void launchSecondActivity(View view)
    {
        Intent intent = new Intent(this, Activity2.class);

        intent.putExtra("com.example.android.Atividade2.extra.MESSAGE", et.getText().toString());

        startActivity(intent);
    }
}