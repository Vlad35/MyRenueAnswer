package Classes;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class Main {

    @Value("${app.defColumn}")
    static int defaultColumn;

    @Value("${app.columnSize}")
    static int columnSize;
    static final String filePath = "airports.dat";


    public static void main(String[] args) throws CsvValidationException, IOException {
        SpringApplication.run(Main.class, args);

        int argLen = args.length;
        int firstArg = 0;
        if(argLen > 0) {
            firstArg = Integer.parseInt(args[0]);
        }

        Scanner sc = new Scanner(System.in);
        MyCSV rd = new MyCSV(filePath, getColumn(),argLen,firstArg);
            System.out.println("\nВведите строку:");
            String key = sc.nextLine();
            long wastedTime = rd.outputByValue(key);
            System.out.println("Время, затраченное на поиск и сортировку: " + (wastedTime / 1000000) + " мс");
    }

    public void setColumn(int column) {
        this.defaultColumn = column;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public static int getColumn() {
        return defaultColumn;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public static String getFilePath() {
        return filePath;
    }
}
