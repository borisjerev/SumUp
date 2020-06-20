package com.borisjerev.sumUp.model;

import java.util.List;

public class TaskRequest {
    private String name;
    private String command;
    private List<String> requires;

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getRequires() {
        return requires;
    }

    public void setRequires(List<String> requires) {
        this.requires = requires;
    }
}
