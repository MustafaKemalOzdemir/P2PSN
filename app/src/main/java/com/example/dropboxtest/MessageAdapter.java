package com.example.dropboxtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder>{

    ArrayList<MessageSample> messageSamples;

    public MessageAdapter(ArrayList<MessageSample> messageSamples){
        this.messageSamples=messageSamples;

    }


    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.right_item_layout,viewGroup,false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, int i) {
        messageViewHolder.messageText.setText(messageSamples.get(i).getMessageText());
        messageViewHolder.name.setText(messageSamples.get(i).getName());
        Date date=new Date(Long.parseLong(messageSamples.get(i).getTime()));
        Format format=new SimpleDateFormat("MM dd - HH:mm", Locale.ENGLISH);

        messageViewHolder.time.setText(format.format(date));

    }

    @Override
    public int getItemCount() {
        return messageSamples.size();
    }
}
class MessageViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView time;
    TextView messageText;
    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        time=itemView.findViewById(R.id.txtTimeRight);
        name=itemView.findViewById(R.id.txtUserRight);
        messageText=itemView.findViewById(R.id.txtMessageRight);

    }
}
