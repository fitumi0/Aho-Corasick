package org.vyatsu.ahocorasick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TrieNode {
    public Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    public TrieNode suffixLink;
    public TrieNode outputLink;
    public boolean isTerminal;
    public List<String> patterns;

    public TrieNode() {
        this.children = new HashMap<>();
        this.suffixLink = null;
        this.outputLink = null;
        this.isTerminal = false;
        this.patterns = new ArrayList<>();
    }
}
