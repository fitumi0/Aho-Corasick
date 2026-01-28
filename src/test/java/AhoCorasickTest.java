import java.util.Arrays;
import java.util.List;

import org.vyatsu.ahocorasick.AhoCorasickContext;
import org.vyatsu.ahocorasick.Trie;
import org.vyatsu.ahocorasick.TrieNode;
import org.vyatsu.ahocorasick.strategies.StandardSearchStrategy;
import org.vyatsu.ahocorasick.structures.Pair;

public class AhoCorasickTest {
    public static void main(String[] args) throws InterruptedException {
        List<String> patterns = Arrays.asList("he", "she", "her", "sher");
        String text = new String(new char[10000]).replace("\0", "shersher");
        
        Trie trie = new Trie();
        TrieNode root = trie.buildTrie(patterns, true);
        
        AhoCorasickContext ctx = new AhoCorasickContext(root, new StandardSearchStrategy());
        List<Pair> results = ctx.search(text);
    }
}
