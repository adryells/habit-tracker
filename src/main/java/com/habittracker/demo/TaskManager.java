package com.habittracker.demo;

import com.habittracker.demo.models.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class TaskManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/tracker";
    private static final String USER = "trackerman";
    private static final String PASSWORD = "123456";

    public TaskManager() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS tasks (id SERIAL PRIMARY KEY, " +
                    "description TEXT NOT NULL, is_completed BOOLEAN DEFAULT FALSE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String description) {
        String sql = "INSERT INTO tasks (description) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, description);
            pstmt.executeUpdate();
            System.out.println("Task added: " + description);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> listTasks() {
        String sql = "SELECT * FROM tasks";
        List<Task> tasks = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getBoolean("is_completed")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void markTaskComplete(int taskId) {
        String sql = "UPDATE tasks SET is_completed = TRUE WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, taskId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Task marked as complete.");
            } else {
                System.out.println("Task ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, taskId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Task removed.");
            } else {
                System.out.println("Task ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editTask(int taskId, String newDescription) {
        String sql = "UPDATE tasks SET description = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newDescription);
            pstmt.setInt(2, taskId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Task updated.");
            } else {
                System.out.println("Task ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}