package org.vyatsu.ahocorasick;

import java.util.HashMap;
import java.util.HashSet;

public class TrieNode {
    private HashMap<Character, TrieNode> child = new HashMap<>();
    private TrieNode suffixLink;
    private TrieNode outputLink;
    private HashSet<String> patterns = new HashSet<>();
    private boolean isTerminal;

    public void setChild(Character key, TrieNode node) {
        this.child.put(key, node);
    }

    public TrieNode getChild(Character key) {
        return this.child.get(key);
    }

    public boolean hasChild(Character key) {
        return this.child.get(key) != null;
    }
    
    public void setSuffixLink(TrieNode suffixLink) {
        this.suffixLink = suffixLink;
    }
    
    public TrieNode getSuffixLink() {
        return this.suffixLink;
    }
    
    public HashSet<String> getPatterns() {
        return this.patterns;
    }
    
    public void addPattern(String word) {
        this.patterns.add(word);
    }

    public void copyPatterns(TrieNode node) {
        for (String pattern : this.patterns) {
            this.patterns.add(pattern);
        }
    }
}
