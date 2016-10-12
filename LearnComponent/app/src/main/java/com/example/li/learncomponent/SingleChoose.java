package com.example.li.learncomponent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class SingleChoose extends AppCompatActivity {

    private Button btnSubmit;
    private RadioButton rA;
    private RadioButton rB;
    private RadioButton rC;
    private RadioButton rD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choose);

        btnSubmit = (Button)findViewById(R.id.submit);
        rA = (RadioButton)findViewById(R.id.rA);
        rB = (RadioButton)findViewById(R.id.rB);
        rC = (RadioButton)findViewById(R.id.rC);
        rD = (RadioButton)findViewById(R.id.rD);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rA.isChecked()) {
                    Toast.makeText(SingleChoose.this, "所选是正确的", Toast.LENGTH_SHORT).show();
                    System.out.println("True");
                }
                else {
                    Toast.makeText(SingleChoose.this, "所选是错误的", Toast.LENGTH_SHORT).show();
                    System.out.println("False");
                }
            }
        });

    }
}
