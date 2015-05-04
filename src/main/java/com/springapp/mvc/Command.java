package com.springapp.mvc;

public class Command {
    private int id;
    private String content;

    public Command() {}

    public Command(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Command {\n" +
                "\"id\": " + id + ",\n" +
                "\"content\": \"" + content + "\"\n" +
                '}';
    }

}
