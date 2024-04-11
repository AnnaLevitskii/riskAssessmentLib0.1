# riskAssessmentLib0.1

riskAssessmentLib/riskAssessment.txt 

|          PossibleFailureName           |  RiskAssessment  |  TotalQuantityFailures   |  Severity  |
| --- | --- |--- | --- |
|   negativeTriangleTest_likely values   |      MINOR       |           null           |   MAJOR    |
|  negativeTriangleTest_unlikely values  |      MINOR       |            12            |   MINOR    |
|       positive triangleTest fail       |     CRITICAL     |            44            |  CRITICAL  |


TotalRiskAssessment:  0.6749999999999999

riskSoftAssert.assertEquals(Triangle.getTriangleType(80, 50, 51), TriangleType.INVALID);
riskSoftAssert.assertEquals(Triangle.getTriangleType(80, 50, 49), TriangleType.INVALID);
riskSoftAssert.assertRisk("negativeTriangleTest_likely values", SeverityType.MAJOR);

riskSoftAssert.assertEquals(Triangle.getTriangleType(-1, 60, 60), TriangleType.INVALID);
riskSoftAssert.assertEquals(Triangle.getTriangleType(0, 30, 30), TriangleType.INVALID);
riskSoftAssert.assertEquals(Triangle.getTriangleType(0, 100, 80), TriangleType.INVALID);
riskSoftAssert.assertEquals(Triangle.getTriangleType(-1, 100, 81), TriangleType.INVALID);
riskSoftAssert.assertRisk("negativeTriangleTest_unlikely values", SeverityType.MINOR);

riskSoftAssert.assertAll("triangleTest_negative");







<dependency>
            <groupId>riskAssessmentLib</groupId>
            <artifactId>riskAssessmentLib</artifactId>
            <version>0.1</version>
            <type>jar</type>
            <scope>system</scope> 
            <systemPath>/riskAssessmentLib0.1/lib/build/libs/lib.jar</systemPath>
</dependency>

implementation files('/riskAssessmentLib0.1/lib/build/libs/lib.jar')


gradle build