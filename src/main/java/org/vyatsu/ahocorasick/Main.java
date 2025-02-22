package org.vyatsu.ahocorasick;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
         // Список шаблонов для поиска
        List<String> patterns = Arrays.asList("Hell", "war", "ld", "!");
        
        // Создаем экземпляр бора и строим его
        Trie trie = new Trie();
        TrieNode root = trie.buildTrie(patterns);

        System.out.println(root.getChild('H'));

        AhoCorasick ac = new AhoCorasick(root);
    }
}

