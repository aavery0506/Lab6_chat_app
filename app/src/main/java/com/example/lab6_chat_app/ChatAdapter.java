package com.example.lab6_chat_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages){
        this.messages = messages;
    }
    static class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView messageText1;
        TextView messageText2;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText1 = itemView.findViewById(R.id.messageText1);
            messageText2 = itemView.findViewById(R.id.messageText2);
        }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {

        holder.messageText1.setText(this.messages.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void updateAdapter(List<ChatMessage> messages){
        this.messages.clear();
        for( ChatMessage temp : messages){
            messages.add(temp);
        }
        notifyDataSetChanged();
    }


}
