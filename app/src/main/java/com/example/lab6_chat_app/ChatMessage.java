package com.example.lab6_chat_app;

public class ChatMessage {
    private String message, sender;

    public ChatMessage(){}

    public ChatMessage(String message, String sender){
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

}
