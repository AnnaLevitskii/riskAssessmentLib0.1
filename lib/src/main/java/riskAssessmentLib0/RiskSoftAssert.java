package riskAssessmentLib0;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Maps;
import riskAssessmentLib0.utils.Reader;
import riskAssessmentLib0.utils.Writer;

import java.util.Iterator;
import java.util.Map;

public class RiskSoftAssert extends SoftAssert {
    private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();

    int i = 0;
    int j = 0;

    private static final String DEFAULT_SOFT_ASSERT_MESSAGE = "The following asserts failed:";

    public RiskSoftAssert() {

    }

    protected void doAssert(IAssert<?> a) {
        this.onBeforeAssert(a);
        j++;
        try {
            a.doAssert();
            this.onAssertSuccess(a);
        } catch (AssertionError var6) {
            this.onAssertFailure(a, var6);
            this.m_errors.put(var6, a);
            ++i;
        } finally {
            this.onAfterAssert(a);
        }

    }

    public void assertAll() {
        this.assertAll((String)null);
    }

    public void assertAll(String message) {
        Risk.numTests=j;
        j=0;
        if (!this.m_errors.isEmpty()) {
            StringBuilder sb = new StringBuilder(null == message ? "The following asserts failed:" : message);
            boolean first = true;
            Iterator var4 = this.m_errors.keySet().iterator();

            while(var4.hasNext()) {
                AssertionError error = (AssertionError)var4.next();
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }

                sb.append("\n\t");
                sb.append(super.getErrorDetails(error));
            }
            Writer.write();

            throw new AssertionError(sb.toString());
        }
    }
    public void assertRisk(String what, SeverityType severity) {
        Reader.readHashMap();
        if(!Risk.severityMap.containsKey(what))
            Risk.severityMap.put(what,severity);
        if (i>0) {
            if(!Risk.quantityMap.containsKey(what)){
                Risk.quantityMap.put(what, i/Risk.numRuns);
            }else {
                int temp = Risk.quantityMap.get(what);
                Risk.quantityMap.put(what, temp+i);
            }
        }
        i=0;
    }

    protected String getErrorDetails(Throwable error) {
        StringBuilder sb = new StringBuilder();
        sb.append(error.getMessage());

        for(Throwable cause = error.getCause(); cause != null; cause = cause.getCause()) {
            sb.append(" ").append(cause.getMessage());
            sb.append(" ").append(cause.getClass().getName());
        }

        return sb.toString();
    }
}
