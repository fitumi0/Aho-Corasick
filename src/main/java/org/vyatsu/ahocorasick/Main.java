package org.vyatsu.ahocorasick;

import java.util.Arrays;
import java.util.List;

import org.vyatsu.ahocorasick.strategies.IgnoreCaseSearchStrategy;
import org.vyatsu.ahocorasick.structures.Pair;


public class Main {
    public static void main(String[] args) {
         // Search templates
        List<String> patterns = Arrays.asList("he", "she", "her", "sher");
        String text = "sheRsher";
        
        Trie trie = new Trie();
        TrieNode root = trie.buildTrie(patterns, true);

        AhoCorasickContext ctx = new AhoCorasickContext(root, new IgnoreCaseSearchStrategy());
        List<Pair> results = ctx.search(text);
        
        
        System.out.println("Search Results:");

        for (Pair pair : results) {
            System.out.println(pair);
        }
    }
}

