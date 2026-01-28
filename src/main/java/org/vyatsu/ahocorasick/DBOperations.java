package org.vyatsu.ahocorasick;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.vyatsu.ahocorasick.drivers.PGDriver;

public class DBOperations {

    public int addPattern(String patternText) throws SQLException {
        String sql = "INSERT INTO patterns (pattern_text) VALUES (?) RETURNING pattern_id";
        try (Connection conn = PGDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patternText);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("pattern_id");
            }
        }
        return -1;
    }

    public List<String> getPatterns() throws SQLException {
        String sql = "SELECT pattern_text FROM patterns";
        List<String> patterns = new ArrayList<>();
        try (Connection conn = PGDriver.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                patterns.add(rs.getString("pattern_text"));
            }
        }
        return patterns;
    }

    public int addText(String textContent) throws SQLException {
        String sql = "INSERT INTO texts (text_content) VALUES (?) RETURNING text_id";
        try (Connection conn = PGDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, textContent);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("text_id");
            }
        }
        return -1;
    }

    public List<String> getTexts() throws SQLException {
        String sql = "SELECT text_content FROM texts";
        List<String> texts = new ArrayList<>();
        try (Connection conn = PGDriver.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                texts.add(rs.getString("text_content"));
            }
        }
        return texts;
    }

    public void addResult(int textId, int patternId, int position) throws SQLException {
        String sql = "INSERT INTO results (text_id, pattern_id, position) VALUES (?, ?, ?)";
        try (Connection conn = PGDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, textId);
            stmt.setInt(2, patternId);
            stmt.setInt(3, position);
            stmt.executeUpdate();
        }
    }

    public int getPatternIdByValue(String patternText) throws SQLException {
        String sql = "SELECT pattern_id FROM patterns WHERE pattern_text = ?";
        try (Connection conn = PGDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patternText);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("pattern_id");
            }
        }
        return -1; // если шаблон не найден
    }
}
