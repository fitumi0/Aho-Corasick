package org.vyatsu.ahocorasick;

import java.util.Arrays;
import java.util.List;

import org.vyatsu.ahocorasick.utils.Pair;

public class Main {
    public static void main(String[] args) {
         // Search templates
        List<String> patterns = Arrays.asList("he", "she", "her");
        String text = "shershe";
        
        Trie trie = new Trie();
        TrieNode root = trie.buildTrie(patterns);

        AhoCorasick ac = new AhoCorasick(root);
        
        List<Pair> results = ac.search(text);
        
        System.out.println("Search Results:");

        for (Pair pair : results) {
            System.out.println(pair);
        }
    }
}

