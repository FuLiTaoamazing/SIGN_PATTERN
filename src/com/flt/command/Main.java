package com.flt.command;

public class Main {
    public static void main(String[] args) {
        Content content = new Content("hello !");
        Command command=new CopyCommand(content);
        command.exec();
        System.out.println(content.content);
        command.undo();
        System.out.println(content.content);
    }
}
