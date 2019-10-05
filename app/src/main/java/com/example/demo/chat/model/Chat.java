package com.example.demo.chat.model;

public class Chat {
    String messager;
    String receiver;
    String sender;

    public Chat() {
    }

    public Chat(String messager, String receiver, String sender) {
        this.messager = messager;
        this.receiver = receiver;
        this.sender = sender;
    }

    public String getMessager() {
        return messager;
    }

    public void setMessager(String messager) {
        this.messager = messager;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
