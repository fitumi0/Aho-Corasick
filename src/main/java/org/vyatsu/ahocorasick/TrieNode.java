package org.vyatsu.ahocorasick;

import java.util.HashMap;
import java.util.HashSet;

public class TrieNode {
    private HashMap<Character, TrieNode> children = new HashMap<>();
    private TrieNode suffixLink;
    private HashSet<String> outputs = new HashSet<>();

    public boolean hasChild(Character key) {
        return this.children.get(key) != null;
    }

    public TrieNode getChild(Character key) {
        return this.children.get(key);
    }

    public void setChild(Character key, TrieNode node) {
        this.children.put(key, node);
    }

    public HashMap<Character, TrieNode> getChildren(){
        return this.children;
    }
    
    public void setSuffixLink(TrieNode suffixLink) {
        this.suffixLink = suffixLink;
    }
    
    public TrieNode getSuffixLink() {
        return this.suffixLink;
    }
    
    public HashSet<String> getOutputs() {
        return this.outputs;
    }
    
    public void addOutput(String word) {
        this.outputs.add(word);
    }

    public void copyOutputs(TrieNode node) {
        for (String output : this.outputs) {
            this.outputs.add(output);
        }
    }
}
