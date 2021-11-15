package com.example.foldermanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ClipData;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycle;
    private adapter_folder adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycle = findViewById(R.id.recyclerview);


        recycle.setLayoutManager(new LinearLayoutManager(this));

        adapter = new adapter_folder(this);
        recycle.setAdapter(adapter);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutoolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( R.id.add == item.getItemId()) {

            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dia);

            dialog.setCancelable(false);

            EditText edit = dialog.findViewById(R.id.edit);
            Button no = dialog.findViewById(R.id.no);
            Button yes = dialog.findViewById(R.id.yes);


            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<folder> folders = adapter.get();
                    if (edit.getText().toString() != "") {
                        folders.add(new folder(edit.getText().toString()));
                        adapter.set(folders);
                        Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else
                        Toast.makeText(MainActivity.this, "empty folder's name!!!", Toast.LENGTH_SHORT).show();


                }
            });
            dialog.show();

        }
        return true;
    }
}