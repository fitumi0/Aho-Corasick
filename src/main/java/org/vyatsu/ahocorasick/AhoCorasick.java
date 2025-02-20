package org.vyatsu.ahocorasick;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author fitumi0
 */
public class AhoCorasick {
    private TrieNode root;

    public AhoCorasick(TrieNode root) {
        this.root = root;
        buildSuffixLinks();
    }

    private void buildSuffixLinks() {
        Queue<TrieNode> queue = new LinkedList<>();
        
        root.setSuffixLink(root);

        // For depth-1 all node's suffix links = root
        for (TrieNode child : this.root.getChildren().values()) {
            child.setSuffixLink(root);
            queue.add(child);
        }
        
        // BFS
        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();
            for (Map.Entry<Character, TrieNode> entry : current.getChildren().entrySet()) {
                TrieNode child = entry.getValue();
                
                queue.add(child);              
            }
        }
    }
}
