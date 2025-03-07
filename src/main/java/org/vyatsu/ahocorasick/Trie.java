package org.vyatsu.ahocorasick;

import java.util.List;

public class Trie {
    private TrieNode root = new TrieNode();

    public TrieNode buildTrie(List<String> patterns) {
        TrieNode current = this.root;

        for (String pattern : patterns) {
            for (char c : pattern.toCharArray()) {
                if (!current.hasChild(c)) {
                    current.setChild(c, new TrieNode());
                }

                current = current.getChild(c);
            }

            current.addOutput(pattern);
            current = this.root;
        }

        return current;
    }

    public TrieNode getRoot() {
        return root;
    }

    public static void visualizeTrie(TrieNode root){
        
    }
}
