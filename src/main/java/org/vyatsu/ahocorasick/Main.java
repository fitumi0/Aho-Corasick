package org.vyatsu.ahocorasick;

import java.util.Arrays;
import java.util.List;

import org.vyatsu.ahocorasick.structures.Pair;


public class Main {
    public static void main(String[] args) {
         // Search templates
        List<String> patterns = Arrays.asList("he", "she", "her", "sher");
        String text = "shersher";
        
        Trie trie = new Trie();
        TrieNode root = trie.buildTrie(patterns, true);

        Trie.visualizeTrie(root, 0);

        AhoCorasick ac = new AhoCorasick(root);
        
        List<Pair> results = ac.search(text, true);
        
        System.out.println("Search Results:");

        for (Pair pair : results) {
            System.out.println(pair);
        }
    }
}

