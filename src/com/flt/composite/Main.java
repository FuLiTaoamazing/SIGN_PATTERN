package com.flt.composite;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BranchNode root = new BranchNode("root");
        BranchNode capter1 = new BranchNode("capter1");
        BranchNode capter2 = new BranchNode("capter2");
        BranchNode section = new BranchNode("section");
        Node c11 = new LeafNode("c11");
        Node c12 = new LeafNode("c12");
        Node c21 = new LeafNode("c21");
        Node c22 = new LeafNode("c22");
        root.add(capter1).add(capter2);
        capter1.add(c11).add(c12);
        capter2.add(section);
        section.add(c21).add(c22);
        tree(root,0);

    }

    static void tree(Node root, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("--");
        }
        root.print();
        if (root instanceof BranchNode) {
            for (Node child : ((BranchNode) root).childs) {
                tree(child, depth + 1);
            }
        }
    }


}

interface Node {
    void print();
}

class LeafNode implements Node {
    String nodeName;

    public LeafNode(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public void print() {
        System.out.println(nodeName);
    }
}

class BranchNode implements Node {
    String nodeName;
    List<Node> childs;

    public BranchNode(String nodeName) {
        this.nodeName = nodeName;
        this.childs = new ArrayList<>();
    }

    public BranchNode add(Node node) {
        childs.add(node);
        return this;
    }

    @Override
    public void print() {
        System.out.println(nodeName);
    }
}