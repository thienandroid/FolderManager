package com.example.foldermanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class adapter_folder extends RecyclerView.Adapter<adapter_folder.myviewholder> {

    private Context context;
    private ArrayList<folder> folders = new ArrayList<>();

    public adapter_folder(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.folder_item, parent,false);
        myviewholder viewholder = new myviewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        folders = get();
        folder folder = folders.get(position);
        holder.name.setText(folder.getName());

        holder.pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.name.setText("");
                set(folders);
                //notifyItemChanged(holder.getPosition());
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                folders.remove(holder.getPosition());
                set(folders);
                notifyItemRemoved(holder.getPosition());
            }
        }
        );
    }

    @Override
    public int getItemCount() {
        return this.get().size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView pencil;
        private ImageView cancel;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv);
            pencil = itemView.findViewById(R.id.pencil);
            cancel = itemView.findViewById(R.id.cancel);
        }
    }

    static final String key = "key";

    public void set(ArrayList<folder> folders){
        //this.folders = folders;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String jsonfolder = gson.toJsonTree(folders).getAsJsonArray().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,jsonfolder);
        editor.apply();
        //Log.d("thien", jsonfolder);
    }

    public ArrayList<folder> get()  {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonfolder = sharedPreferences.getString(key,"");
        ArrayList<folder> folders = new ArrayList<>();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonfolder);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        Gson gson = new Gson();
        for(int i=0;i<jsonArray.length();i++){
            try {
                jsonObject = jsonArray.getJSONObject(i);
                folder folder = gson.fromJson(jsonObject.toString(), com.example.foldermanager.folder.class);
                folders.add(folder);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return folders;

    }
}
