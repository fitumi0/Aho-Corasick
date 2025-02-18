package org.vyatsu.ahocorasick;

public class TrieNode {
    public TrieNode children;
    public boolean isTerminal;
    public char data;    

    public void setChildren(char data) {
        this.children = new TrieNode();
        this.children.data = data;
    }
}
