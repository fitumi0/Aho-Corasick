package org.vyatsu.ahocorasick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrieNode {
    private HashMap<Character, TrieNode> children = new HashMap<>();
    private TrieNode suffixLink;
    private TrieNode outputLink;
    private List<String> patterns = new ArrayList<>();
    private boolean isTerminal;

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }
    
    public void setSuffixLink(TrieNode suffixLink) {
        this.suffixLink = suffixLink;
    }
    
    public TrieNode getSuffixLink() {
        return suffixLink;
    }
    
    public List<String> getPattern() {
        return patterns;
    }
    
    public void addPattern(String word) {
        patterns.add(word);
    }
}
