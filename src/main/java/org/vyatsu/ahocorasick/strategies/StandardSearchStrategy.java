package org.vyatsu.ahocorasick.strategies;

import java.util.List;

import org.vyatsu.ahocorasick.AhoCorasick;
import org.vyatsu.ahocorasick.TrieNode;
import org.vyatsu.ahocorasick.interfaces.SearchStrategy;
import org.vyatsu.ahocorasick.structures.Pair;

public class StandardSearchStrategy implements SearchStrategy {
    @Override
    public List<Pair> search(String text, TrieNode root) {
        AhoCorasick ac = new AhoCorasick(root);
        return ac.search(text, false);
    }
}
