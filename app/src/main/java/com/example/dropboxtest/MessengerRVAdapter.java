package com.example.dropboxtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MessengerRVAdapter extends RecyclerView.Adapter<MessengerRVViewHolder>{


    private ArrayList<Friend> arrayList;
    private OnItemClickListener onItemClickListener;


    MessengerRVAdapter(Context context,ArrayList<Friend> friends,OnItemClickListener listener){
        arrayList=friends;
        onItemClickListener=listener;
       Log.v("rvtest","calledConstruct");
       Log.v("rvtest",friends.size()+"=size");

    }


    @NonNull
    @Override
    public MessengerRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.v("rvtest","oncreate");
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.messenger_list_item,viewGroup,false);
        return new MessengerRVViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MessengerRVViewHolder messengerRVViewHolder, int i) {
        Log.v("rvtest","called");
        String name=arrayList.get(i).getName();
        Log.v("rvtest",name);
        messengerRVViewHolder.name.setText(name);

    }

    @Override
    public int getItemCount() {
        Log.v("rvtest",arrayList.size()+"==Size");
        return arrayList.size();
    }
    public void updateList(ArrayList<Friend> array){
        arrayList=new ArrayList<>();
        arrayList.addAll(array);
        notifyDataSetChanged();


    }
}
class MessengerRVViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
    TextView name;
    private OnItemClickListener mListener   ;

    public MessengerRVViewHolder(@NonNull View itemView,OnItemClickListener listener) {
        super(itemView);
        mListener=listener;
        name=itemView.findViewById(R.id.MessengerMenuItemText);
        itemView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int clickedPosition = getAdapterPosition();
        mListener.onListItemClick(clickedPosition);

    }
}
