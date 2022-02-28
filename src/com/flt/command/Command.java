package com.flt.command;

public abstract class Command {
    protected Content content;

    public Command(Content content) {
        this.content = content;
    }

    public abstract void exec();

    public abstract void undo();
}
