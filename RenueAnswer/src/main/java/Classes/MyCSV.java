package Classes;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class MyCSV {
    private int column;

    private String filePath;

    @Value("${app.columnSize}")
    int columnSize;

    private int argsNumber;

    private int firstArg;

    public MyCSV(String filePath, int column, int argsNumber, int firstArg) {
            this.column = column;
            this.filePath = filePath;
            this.argsNumber = argsNumber;
            this.firstArg = firstArg;
    }

    public long outputByValue(String key) throws IOException, CsvValidationException {
        int column = this.getColumn();
        if (this.getArgsNumber() > 0) {
            try {
                this.setColumn(this.getFirstArg());
            } catch (NumberFormatException e) {
                System.out.println("Your argument is illegal");
                return -1;
            }
            if (this.getColumn() < 0 || this.getColumn() >= this.getColumnSize()) {
                System.out.println("There's no such a column");
                return -1;
            }
        }
        CSVReader csvReader = new CSVReader(new FileReader(getFilePath(), StandardCharsets.UTF_8));
        Tree tree = new Tree();
        String[] newLine;
        while ((newLine = csvReader.readNext()) != null) {
            tree.put(newLine, this.getColumn());
        }
        csvReader.close();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String input = sc.nextLine();
        long startTime = System.nanoTime();
        var resLines = tree.getAllStartingWith(input);
        long endTime = System.nanoTime();
        for (var line : resLines) {
            System.out.println(Arrays.toString(line));
        }
        long resTime = endTime - startTime;
        System.out.println("Number of found rows: " + resLines.size() + ".");
        System.out.println("Wasted time: ");
        return resTime;
    }

    public void setArgsNumber(int argsNumber) {
        this.argsNumber = argsNumber;
    }

    public void setFirstArg(int firstArg) {
        this.firstArg = firstArg;
    }

    public int getArgsNumber() {
        return argsNumber;
    }

    public int getFirstArg() {
        return firstArg;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getColumn() {
        return column;
    }

    public String getFilePath() {
        return filePath;
    }
}
