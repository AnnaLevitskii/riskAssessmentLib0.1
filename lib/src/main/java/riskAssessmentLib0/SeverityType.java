package riskAssessmentLib0;

import org.apache.commons.math3.exception.MathRuntimeException;

public enum SeverityType {
    BLOCKER(1.0) ,
    CRITICAL(0.75) ,
    MAJOR(0.5) ,
    MINOR(0.25);
    private final Double val;
    SeverityType(Double val){
        this.val=val;
    }

    public  double getVal(){
        return val;
    }
    public static SeverityType getSeverityType(Double val){
        if(val>1 || val<0) throw new RuntimeException("val getSeverityType is wrong " + val);
        if(val<=0.25) return MINOR;
        if(val<=0.5) return MAJOR;
        if(val<= 0.75) return CRITICAL;
        return BLOCKER;

    }


}
