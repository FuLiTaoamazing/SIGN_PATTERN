package com.flt.command;

public class InsertCommand extends Command {
    private String insertStr = "Fulitao is amazing";

    public InsertCommand(Content content) {
        super(content);
    }

    @Override
    public void exec() {
        content.content = content.content + insertStr;
    }

    @Override
    public void undo() {
        content.content = content.content.substring(0, content.content.length() - insertStr.length());
    }
}
