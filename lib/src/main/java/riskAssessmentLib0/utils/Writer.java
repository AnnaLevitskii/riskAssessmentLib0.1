package riskAssessmentLib0.utils;

import riskAssessmentLib0.Risk;
import riskAssessmentLib0.SeverityType;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Writer {
    public Writer() {

    }

    static String directoryPath = "riskAssessmentLib";
    static File directory = new File(directoryPath);
    static String tabFileName = "riskAssessmentLib/riskAssessment.txt";
    static File tabFile = new File(tabFileName);
    public static String numRunsFileName = "riskAssessmentLib/numRuns.txt";
    static File numRunsFile = new File(numRunsFileName);
    public static String quantityFileName = "riskAssessmentLib/quantity.ser";
    static File quantityFile = new File(quantityFileName);





    public static void createFiles() {
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!tabFile.exists()) {
                    tabFile.createNewFile();
            }
            if (!numRunsFile.exists()) {
                numRunsFile.createNewFile();
            }
            if (!quantityFile.exists()) {
                quantityFile.createNewFile();
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeInFile(String str, String fileName) {
        createFiles();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            System.out.println("e in write str- "+ str);
        }
    }
    public static void write(){
        TabBuilder tabBuilder = new TabBuilder();

        List<String> headersList = new ArrayList<>();
        headersList.add("PossibleFailureName");
        headersList.add("RiskAssessment");
        headersList.add("TotalQuantityFailures");
        headersList.add("Severity");

        List<List<String>> rowsList = new ArrayList<>();

        Risk.severityMap.forEach((key, value) -> {
            List<String> row = new ArrayList<>();
            row.add(key);
            row.add(Risk.getRisk(key).toString());
            row.add(String.valueOf(Risk.quantityMap.get(key)));
            row.add(value.toString());
            rowsList.add(row);
        });
        writeInFile(tabBuilder.generateTable(headersList, rowsList) + "\n TotalRiskAssessment:  "+Risk.getTotalRiskAssessment(), tabFileName);
        writeHashMap();
    }
    public static void writeHashMap() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(quantityFileName))) {
            oos.writeObject(Risk.quantityMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
