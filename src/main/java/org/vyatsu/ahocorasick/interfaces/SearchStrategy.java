package org.vyatsu.ahocorasick.interfaces;

import java.util.List;

import org.vyatsu.ahocorasick.TrieNode;
import org.vyatsu.ahocorasick.structures.Pair;

public interface SearchStrategy {
    List<Pair> search(String text, TrieNode root);
}
