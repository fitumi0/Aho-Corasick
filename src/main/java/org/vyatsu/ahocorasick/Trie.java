package org.vyatsu.ahocorasick;

import java.util.List;
import java.util.Map.Entry;

public class Trie {
    private TrieNode root = new TrieNode();

    public TrieNode buildTrie(List<String> patterns, boolean ignoreCase) {
        TrieNode current = this.root;

        for (String pattern : patterns) {
            if (ignoreCase) {
                pattern = pattern.toLowerCase();
            }

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

    public static void visualizeTrie(TrieNode root, int level) {
        if (root == null) {
            return;
        }

        // la cringe output
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }

        System.out.println(root.getOutputs().toString());
        for (Entry<Character, TrieNode> entry : root.getChildren().entrySet()) {
            visualizeTrie(entry.getValue(), level + 1);
        }
    }
}
