package org.vyatsu.ahocorasick;

import java.util.LinkedList;
import java.util.List;
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
    
        // The root is suffix link for all root childs
        for (Map.Entry<Character, TrieNode> entry : this.root.getChildren().entrySet()) {
            TrieNode child = entry.getValue();
            char character = entry.getKey();
    
            child.setSuffixLink(root);
            queue.add(child);
        }
    
        // BFS
        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();
    
            // For all child from current
            for (Map.Entry<Character, TrieNode> entry : current.getChildren().entrySet()) {
                TrieNode child = entry.getValue();
                char key = entry.getKey();

                queue.add(child);
                
                // Find the suffix link for current's child
                TrieNode suffixLink = current.getSuffixLink();
                
                // Follow the suffix links of CURRENT element, NOT CHILD and find with the same character child 
                // ps: dumbass (me) couldn't figure out how to follow a suffix link from a CHILD node (it's empty)
                // 
                while(!suffixLink.hasChild(key) && suffixLink != this.root) {
                    suffixLink = suffixLink.getSuffixLink();
                }

                // If found, need set child's suffix link to suffix link's child. else - to root 
                child.setSuffixLink(suffixLink.hasChild(key) ? suffixLink.getChild(key) : this.root);
                
                // Copying outputs
                child.copyOutputs(child.getSuffixLink());
            }
        }
    }

    public Map<String, List<Integer>> search(String text) {
        return null;
    }
}
