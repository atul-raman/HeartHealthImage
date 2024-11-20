package com.example;

import java.io.*;
import java.nio.file.*;
import java.util.stream.Collectors;

public class FileHandler {

    // Create a file with the given name and write the provided data
    public boolean createFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
            return true;
        } catch (IOException e) {
            System.err.println("Error creating file: " + fileName);
            e.printStackTrace();
            return false;
        }
    }

    // Read the contents of a file and return it as a String
    public String readFile(String fileName) {
        try {
            return Files.lines(Paths.get(fileName)).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
            e.printStackTrace();
            return null;
        }
    }

    // Update a file by overwriting it with the new data
    public boolean updateFile(String fileName, String newData) {
        return createFile(fileName, newData); // Reuses createFile method to overwrite
    }

    // Check if a file exists
    public boolean fileExists(String fileName) {
        return Files.exists(Paths.get(fileName));
    }

    // Append data to an existing file
    public boolean appendToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine(); // Add a new line after appending
            return true;
        } catch (IOException e) {
            System.err.println("Error appending to file: " + fileName);
            e.printStackTrace();
            return false;
        }
    }

    // Delete a file
    public boolean deleteFile(String fileName) {
        try {
            return Files.deleteIfExists(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println("Error deleting file: " + fileName);
            e.printStackTrace();
            return false;
        }
    }
}
