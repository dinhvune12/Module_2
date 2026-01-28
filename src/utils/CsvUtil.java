package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) return lines;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() > 0) lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("thất cmn bại: " + e.getMessage());
        }
        return lines;
    }
    public static void writeLines(String path, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (int i = 0; i < lines.size(); i++) {
                bw.write(lines.get(i));
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("thất bài rồi: " + e.getMessage());
        }
    }
}
