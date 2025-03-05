package org.vyatsu.ahocorasick;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.vyatsu.ahocorasick.utils.Pair;

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

    public List<Pair> search(String text) {
        List<Pair> result = new ArrayList<>();
    
        TrieNode current = this.root;
        int i = 0;
    
        while (i < text.length()) {
            char c = text.charAt(i);
    
            // Follow suffix links if current node not root and not have the char
            while (current != this.root && !current.hasChild(c)) {
                current = current.getSuffixLink();
            }
    
            // If child exists, assign new current
            if (current.hasChild(c)) {
                current = current.getChild(c);
                // And go to next char
                i++; 
    
                // Check for matches in the current node and its suffix links
                TrieNode temp = current;
                while (temp != this.root) {
                    for (String output : temp.getOutputs()) {
                        result.add(new Pair(i - output.length(), output));
                    }

                    // Following a non-root suffix link probably we can find an complete pattern
                    // e.g. for patterns "he", "she" in text "she" will be "she" at 0 pos and "he" at 1 pos
                    temp = temp.getSuffixLink();
                }
            } else {
                // If no match and current is root, move to the next character
                i++;
            }
        }
    
        return result;
    }
    
}
