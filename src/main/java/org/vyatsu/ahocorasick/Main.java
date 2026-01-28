package org.vyatsu.ahocorasick;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.vyatsu.ahocorasick.strategies.StandardSearchStrategy;
import org.vyatsu.ahocorasick.structures.Pair;


public class Main {
    public static void main(String[] args) throws SQLException {
        DBOperations db = new DBOperations();
         try {
            List<String> patterns = Arrays.asList("he", "she", "her", "sher");
            for (String pattern : patterns) {
                db.addPattern(pattern);
            }

            String text = "shersher";
            int textId = db.addText(text);

            Trie trie = new Trie();
            TrieNode root = trie.buildTrie(patterns, true);

            AhoCorasickContext ctx = new AhoCorasickContext(root, new StandardSearchStrategy());
            List<Pair> results = ctx.search(text);

            for (Pair pair : results) {
                String matchedPattern = pair.getValue();

                // Получаем pattern_id напрямую из БД
                int patternId = db.getPatternIdByValue(matchedPattern);
                if (patternId != -1) {
                    db.addResult(textId, patternId, pair.getIndex());
                }
            }

            System.out.println("Search Results:");
            for (Pair pair : results) {
                System.out.println(pair);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
}

