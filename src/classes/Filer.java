package classes;

import interfaces.CsvRow;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

public class Filer {

    private String fileName;

    public Filer(String fileName) {
        this.fileName = fileName;
    }

    //Consumer uzima jednu vrednost i izvrsi neku operaciju na tu vrednost
    public void read(Consumer<String[]> consumer) {
        try {
            FileReader fis = new FileReader(Paths.get(fileName).toFile());
            BufferedReader reader = new BufferedReader(fis);
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    var a = line.split(","); //Uzima niz stringova i splituje ih po ","
                    consumer.accept(a);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<? extends CsvRow> lista bilo kojih klasa koje implementiraju intefejs CsvRow
    public void write(List<? extends CsvRow> data) {
        try {
            FileWriter fis = new FileWriter(Paths.get(fileName).toFile());
            PrintWriter writer = new PrintWriter(fis);
            data.forEach(csvRow -> {
                writer.write(csvRow.toCsv() + "\n");
            });
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
