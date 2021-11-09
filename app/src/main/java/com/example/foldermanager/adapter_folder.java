package com.example.foldermanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import javax.xml.namespace.QName;

public class adapter_folder extends RecyclerView.Adapter<adapter_folder.myviewholder> {

    private Context context;
    private ArrayList<folder> folders;

    public adapter_folder(Context context, ArrayList<folder> folders) {
        this.context = context;
        this.folders = folders;
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
        folder folder = folders.get(position);
        holder.name.setText(folder.getName());

        holder.pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.name.setText(" ");
                //notifyItemChanged(holder.getPosition());
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                folders.remove(holder.getPosition());
                notifyItemRemoved(holder.getPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return folders.size();
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
}
