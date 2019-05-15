package com.example.dropboxtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


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
        messageViewHolder.time.setText(messageSamples.get(i).getTime());

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
