package riskAssessmentLib0;

import riskAssessmentLib0.utils.Reader;
import riskAssessmentLib0.utils.Writer;

import java.util.HashMap;

import static riskAssessmentLib0.utils.Writer.numRunsFileName;

public class Risk {
    public static HashMap<String, SeverityType> severityMap = new HashMap<String, SeverityType>();
    public static HashMap<String, Integer> quantityMap = new HashMap<String, Integer>();
    public static HashMap<String, SeverityType> riskMap = new HashMap<String, SeverityType>();
    public static int numRuns = 0;
    public static int numTests = 0;
    public static Double totalRiskAssessment = 0.; // Can not be expressed in severity

    static {
        numRuns = Reader.readNumRuns();
        Writer.writeInFile(String.valueOf(Risk.numRuns), numRunsFileName);
    }
    public static SeverityType getRisk(String what){
        double Q = (quantityMap.get(what)==null)? 0. : quantityMap.get(what);
        double U = severityMap.get(what).getVal();
        double risk = (((Q/ numRuns)/numTests) * U);
        totalRiskAssessment+=risk;
        riskMap.put(what, SeverityType.getSeverityType(risk));
        return SeverityType.getSeverityType(risk);
    }
    public static Double getTotalRiskAssessment(){
        // Can not be expressed in severity
        return totalRiskAssessment;
    }
}
