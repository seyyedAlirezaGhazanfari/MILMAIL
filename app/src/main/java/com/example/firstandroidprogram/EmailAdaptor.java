package com.example.firstandroidprogram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public  class EmailAdaptor extends RecyclerView.Adapter<EmailAdaptor.ViewHolder> {
   private ArrayList<Email> data;
   private LayoutInflater inflater;
   public EmailAdaptor(Context context , ArrayList<Email> data){
       this.data = data;
       inflater = LayoutInflater.from(context);
   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.email_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     Email email = data.get(position);
     holder.subject.setText(email.getSubject());
     holder.sender.setText(email.getSender());
     //here is usage of picasso for showing image
     Picasso.get().load(email.getImageURL()).resize(150,150).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView sender;
        TextView subject;
        ImageView imageView;
        ViewHolder(View itemView){
            super(itemView);
            sender = itemView.findViewById(R.id.senderInRow);
            subject = itemView.findViewById(R.id.subjectInRow);
            imageView = itemView.findViewById(R.id.imageInRow);

        }
    }
}
