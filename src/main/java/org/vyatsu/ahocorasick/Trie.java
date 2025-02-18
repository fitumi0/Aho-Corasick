package org.vyatsu.ahocorasick;

import java.util.List;

public class Trie {
    private final TrieNode root = new TrieNode();

    public TrieNode buildTrie(List<String> patterns) {
        TrieNode currentNode = this.root;
        for (String pattern : patterns) {
            for (int i = 0; i < pattern.length() - 1; i++) {
                Character key = pattern.charAt(i);
                
                if (!currentNode.hasChild(key)) {
                    currentNode.setChild(key, root);
                }

                currentNode = currentNode.getChild(key);
            }

            currentNode.addPattern(pattern);
            currentNode = this.root;
        }

        return root;
    }

    public TrieNode getRoot() {
        return root;
    }
}
