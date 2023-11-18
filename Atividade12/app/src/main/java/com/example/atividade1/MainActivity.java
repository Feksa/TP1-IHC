package com.example.atividade1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private TextView tv;
    private Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editTextText);
        et2 = (EditText) findViewById(R.id.editTextText2);
        tv  = (TextView) findViewById(R.id.textView);
        bt = (Button) findViewById(R.id.button);

        bt.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int value1 = Integer.parseInt(et1.getText().toString());
                        int value2 = Integer.parseInt(et2.getText().toString());

                        tv.setText(String.valueOf(value1 + value2));
                    }
                });
    }

}