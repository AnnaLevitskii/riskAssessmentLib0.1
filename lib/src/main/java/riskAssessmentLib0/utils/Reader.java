package riskAssessmentLib0.utils;

import riskAssessmentLib0.Risk;

import java.io.*;
import java.util.HashMap;

import static riskAssessmentLib0.utils.Writer.numRunsFileName;
import static riskAssessmentLib0.utils.Writer.quantityFileName;


public class Reader {
    public static int readNumRuns() {
        String line = null;
        int i = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(numRunsFileName));
            line = reader.readLine();
            reader.close();

        } catch (IOException e) {

        }
        if(line==null) return 1;
        else {
            i = Integer.parseInt(line);
            return ++i;
        }
    }

    public static void readHashMap(){
        Writer.createFiles();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(quantityFileName))) {
            Risk.quantityMap = (HashMap) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
