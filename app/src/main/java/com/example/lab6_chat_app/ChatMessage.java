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
        return this.message;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString(){
        return "User: "+ sender + "\n"+ message;
    }
}
