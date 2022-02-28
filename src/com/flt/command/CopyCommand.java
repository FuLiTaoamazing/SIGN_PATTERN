package com.flt.command;

public class CopyCommand extends Command {
    public CopyCommand(Content content) {
        super(content);
    }

    @Override
    public void exec() {
        content.content = content.content + content.content;
    }

    @Override
    public void undo() {
        content.content = content.content.substring(0, content.content.length() / 2);
    }
}
