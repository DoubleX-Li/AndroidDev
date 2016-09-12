package com.example.li.sendargs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TheAty extends AppCompatActivity {

    private TextView tv;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_aty);

        Intent i = getIntent();
//        Bundle data = i.getExtras();
        tv = (TextView)findViewById(R.id.tv);

//        tv.setText(String.format("name=%s age=%d", data.getString("name"), data.getInt("age")));

//        User user = (User)i.getSerializableExtra("data");
        User user = (User)i.getParcelableExtra("data");
        tv.setText(String.format("User info(name=%s, age=%d)", user.getName(), user.getAge()));
        editText = (EditText) findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("data", editText.getText().toString());
                System.out.println("data:" +  editText.getText().toString());
                setResult(1, i);
                finish();
            }
        });
    }
}
