import java.io.*;

public class TextToCSV {

    public static void convertFile(String fileName, String csvFileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter(csvFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] records = line.split("\\$");
                writeToFile(records, bufferedWriter);
            }

            bufferedReader.close();
            bufferedWriter.close();

            System.out.println("File conversion successful!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String[] records, BufferedWriter bufferedWriter) {
        try {
            for (int i = 0; i < records.length; i++) {
                String record = records[i];
                String[] values = record.split("#");
                String valuesRow = String.join(",", values);
                if (i == 0) {
                    bufferedWriter.write("ID," + valuesRow);
                    bufferedWriter.newLine();
                } else {
                    bufferedWriter.write(i + "," + valuesRow);
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        convertFile("..\\students data.txt", "data.csv");
    }
}