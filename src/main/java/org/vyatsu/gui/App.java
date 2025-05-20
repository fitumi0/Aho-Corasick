package org.vyatsu.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vyatsu.ahocorasick.AhoCorasick;
import org.vyatsu.ahocorasick.Trie;
import org.vyatsu.ahocorasick.TrieNode;
import org.vyatsu.ahocorasick.structures.Pair;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class App extends Application {

    private final List<String> patterns = new ArrayList<>();
    private boolean isIgnoreCase = false;
    private VBox patternListContainer;
    private TextArea textArea;
    private TextFlow resultTextFlow;
    private final Color[] patternColors = {
        Color.rgb(255, 0, 0),    // Red
        Color.rgb(0, 128, 0),    // Green
        Color.rgb(0, 0, 255),    // Blue
        Color.rgb(128, 0, 128),  // Purple
        Color.rgb(255, 165, 0),  // Orange
        Color.rgb(0, 128, 128),  // Teal
        Color.rgb(128, 0, 0),    // Maroon
        Color.rgb(0, 0, 128)     // Navy
    };

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: white;");

        VBox leftPanel = createLeftPanel();
        root.setLeft(leftPanel);

        VBox centerPanel = createCenterPanel();
        root.setCenter(centerPanel);

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Aho-Corasick Pattern Search");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createLeftPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));
        panel.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 1px;");
        panel.setPrefWidth(250);

        Label label = new Label("Patterns for search:");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        TextField patternInput = new TextField();
        patternInput.setPromptText("Enter a pattern");
        patternInput.setStyle("-fx-background-color: white;");

        Button addButton = new Button("Add pattern");
        addButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        addButton.setOnAction(event -> {
            String pattern = patternInput.getText().trim();
            if (!pattern.isEmpty()) {
                patterns.add(pattern);
                addPatternToList(pattern);
                patternInput.clear();
            }
        });

        patternListContainer = new VBox(5);
        panel.getChildren().addAll(label, patternInput, addButton, patternListContainer);

        return panel;
    }

    private void addPatternToList(String pattern) {
        HBox patternEntry = new HBox(10);
        patternEntry.setStyle("-fx-background-color: white; -fx-padding: 5px; -fx-border-color: #dee2e6; -fx-border-width: 1px;");
        
        javafx.scene.shape.Rectangle colorIndicator = new javafx.scene.shape.Rectangle(15, 15);
        colorIndicator.setFill(patternColors[patterns.size() - 1]);
        
        Label patternLabel = new Label(pattern);
        patternLabel.setStyle("-fx-font-size: 13px;");
        
        Button removeButton = new Button("x");
        removeButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold;");
        removeButton.setOnAction(event -> {
            patterns.remove(pattern);
            patternListContainer.getChildren().remove(patternEntry);
        });

        patternEntry.getChildren().addAll(colorIndicator, patternLabel, removeButton);
        patternListContainer.getChildren().add(patternEntry);
    }

    private VBox createCenterPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));

        Label inputLabel = new Label("Enter text for search:");
        inputLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        textArea = new TextArea();
        textArea.setPromptText("Enter text for search...");
        textArea.setPrefRowCount(10);
        textArea.setStyle("-fx-font-size: 13px;");

        Button searchButton = new Button("Find patterns");
        searchButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14px;");
        searchButton.setOnAction(event -> performSearch());

        CheckBox ignoreCase = new CheckBox("Ignore case");
        ignoreCase.setStyle("-fx-font-size: 13px;");
        ignoreCase.setOnAction(event -> isIgnoreCase = ignoreCase.isSelected());

        Label resultLabel = new Label("Search results:");
        resultLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        resultTextFlow = new TextFlow();
        resultTextFlow.setPrefHeight(300);
        resultTextFlow.setStyle("-fx-background-color: white; -fx-border-color: #dee2e6; -fx-border-width: 1px; -fx-padding: 10px; -fx-font-size: 13px;");

        panel.getChildren().addAll(inputLabel, textArea, searchButton, ignoreCase, resultLabel, resultTextFlow);
        return panel;
    }

    private void performSearch() {
        resultTextFlow.getChildren().clear();
        String text = textArea.getText();

        if (text.isEmpty()) {
            Text message = new Text("No text to search.");
            message.setStyle("-fx-font-style: italic;");
            resultTextFlow.getChildren().add(message);
            return;
        }
        
        if (patterns.isEmpty()) {
            Text message = new Text("No patterns to search for.");
            message.setStyle("-fx-font-style: italic;");
            resultTextFlow.getChildren().add(message);
            return;
        }

        Trie trie = new Trie();
        TrieNode root = trie.buildTrie(patterns, isIgnoreCase);
        AhoCorasick ac = new AhoCorasick(root);
        List<Pair> results = ac.search(text, isIgnoreCase);

        Map<String, Color> patternColors = new HashMap<>();
        for (int i = 0; i < patterns.size(); i++) {
            patternColors.put(patterns.get(i), this.patternColors[i % this.patternColors.length]);
        }

        results.sort((a, b) -> Integer.compare(a.getIndex(), b.getIndex()));

        final int MAX_CONTEXT_LENGTH = 20;

        for (Pair pair : results) {
            int startIndex = pair.getIndex();
            String pattern = pair.getValue();
            int endIndex = startIndex + pattern.length();


            Text indexText = new Text(startIndex + ": [");
            resultTextFlow.getChildren().add(indexText);

            if (startIndex > MAX_CONTEXT_LENGTH) {
                Text leftContext = new Text("..." + text.substring(startIndex - MAX_CONTEXT_LENGTH, startIndex));
                resultTextFlow.getChildren().add(leftContext);
            } else {
                Text leftContext = new Text(text.substring(0, startIndex));
                resultTextFlow.getChildren().add(leftContext);
            }

            Text patternText = new Text(pattern);
            patternText.setFill(patternColors.get(pattern)); // if match pattern with ignore case - color will not be set. i dont care lmao
            resultTextFlow.getChildren().add(patternText);

            if (endIndex < text.length() - MAX_CONTEXT_LENGTH) {
                Text rightContext = new Text(text.substring(endIndex, endIndex + MAX_CONTEXT_LENGTH) + "...");
                resultTextFlow.getChildren().add(rightContext);
            } else {
                Text rightContext = new Text(text.substring(endIndex));
                resultTextFlow.getChildren().add(rightContext);
            }

            Text closingBracket = new Text("]\n");
            resultTextFlow.getChildren().add(closingBracket);
            
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

