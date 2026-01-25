package org.vyatsu.ahocorasick;

import java.util.List;

import org.vyatsu.ahocorasick.interfaces.SearchStrategy;
import org.vyatsu.ahocorasick.structures.Pair;

public class AhoCorasickContext {
    private final TrieNode root;
    private SearchStrategy strategy;

    public AhoCorasickContext(TrieNode root, SearchStrategy strategy) {
        this.root = root;
        this.strategy = strategy;
    }

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Pair> search(String text) {
        return strategy.search(text, root);
    }
}
