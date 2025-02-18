package org.vyatsu.ahocorasick;

public class Trie {
    public Trie(String[] keys) {
        TrieNode root = new TrieNode();

        for (String string : keys) {
            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);

            }
        }
    }
}
