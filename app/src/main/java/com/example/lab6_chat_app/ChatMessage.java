package com.example.lab6_chat_app;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Map;
import java.util.HashMap;

public class ChatMessage {
    private String message, sender;

    DatabaseReference chatsRef;


    public ChatMessage(){}

    public ChatMessage(String sender, String message){
        this.message = message;
        this.sender = sender;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        chatsRef = database.getReference("chats");
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public void sendMessage(String id, String sender, String messageText){
        Map<String, Object> message = new HashMap<>();
        message.put("senderId", sender);
        message.put("text", messageText);
        message.put("timestamp", ServerValue.TIMESTAMP);

        // Push generates a unique key for every new message
        chatsRef.child(id).push().setValue(message);

    }

}
