package com.example.dropboxtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dropboxtest.Objects.Friend;
import com.example.dropboxtest.Objects.Group;

import java.util.ArrayList;

public class GroupFilterAdapter extends RecyclerView.Adapter<GroupFilterAdapter.GroupFilterViewHolder> {
    private ArrayList<Group> groups;
    private OnItemClickListener onItemClickListener;
    private ArrayList<Group> clickedGroups;
    public GroupFilterAdapter(ArrayList<Group> groups,ArrayList<Group> clickedGroups,OnItemClickListener onItemClickListener){
        this.groups=groups;
        this.clickedGroups=clickedGroups;
        this.onItemClickListener=onItemClickListener;

    }

    @NonNull
    @Override
    public GroupFilterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_filter_list_item,viewGroup,false);
        return new GroupFilterViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupFilterViewHolder groupFilterViewHolder, int i) {
        groupFilterViewHolder.groupName.setText(groups.get(i).getGroupName());

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }
    public void updateList(ArrayList<Group> array){
        groups=new ArrayList<>();
        groups.addAll(array);
        notifyDataSetChanged();

    }

    public class GroupFilterViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public TextView groupName;
        public CheckBox checkBox;
        private OnItemClickListener mListener;

        public GroupFilterViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            mListener=listener;
            groupName=itemView.findViewById(R.id.group_name);
            checkBox=itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(checkBox.isChecked()){
                checkBox.setChecked(false);
                clickedGroups.remove(groups.get(getAdapterPosition()));
            }else{
                checkBox.setChecked(true);
                clickedGroups.add(groups.get(getAdapterPosition()));

            }
            int clickedPosition = getAdapterPosition();
            mListener.onListItemClick(clickedPosition);

        }
    }
}
