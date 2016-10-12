package com.example.li.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Start!");
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv);
        GetPerson.getPerson(this);
        adapter = new MyAdapter(GetPerson.persons, this);
        listView.setAdapter(adapter);

    }
}
