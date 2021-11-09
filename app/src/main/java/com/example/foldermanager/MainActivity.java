package com.example.foldermanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycle = findViewById(R.id.recyclerview);

        recycle.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<folder> folders = new ArrayList<>();
        folders.add(new folder("Video"));
        folders.add(new folder("Android"));
        folders.add(new folder("Applock"));
        folders.add(new folder("Books"));
        folders.add(new folder("map"));
        folders.add(new folder("Authenticate Using..."));
        folders.add(new folder("New folder"));
        folders.add(new folder("New folder 1"));

        adapter_folder adapter = new adapter_folder(this, folders);
        recycle.setAdapter(adapter);

    }
}