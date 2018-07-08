package listeners;

import org.apache.commons.lang3.StringUtils;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * UI自动化报表监听类，主要用来监听执行结果并存储
 * 另外，在执行完毕后，发送钉钉消息
 * Createdbyshunheon2018/5/24.
 */
public class UIReportListener implements IReporter {
    //测试结果存储位置
    private final String testResultDir = "./testoutput/testresult";

    private TestSummary testSummary;
    private List<UITestResult> testResults;
    private Map<String, Object> params = new HashMap<String, Object>();

    /**
     * 实现IReporter报表生成模块
     *
     * @paramxmlSuites
     * @paramsuites
     * @paramoutputDirectory
     */
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        List<ITestResult> list = new ArrayList<ITestResult>();
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                IResultMap passedTests = testContext.getPassedTests();
                IResultMap failedTests = testContext.getFailedTests();
                IResultMap skippedTests = testContext.getSkippedTests();
                IResultMap failedConfig = testContext.getFailedConfigurations();
                list.addAll(this.listTestResult(passedTests));
                list.addAll(this.listTestResult(failedTests));
                list.addAll(this.listTestResult(skippedTests));
                list.addAll(this.listTestResult(failedConfig));
            }
        }
        this.sort(list);
        this.outputResult(list, testResultDir + "/test.txt");
        testSummary = this.getTestSummary(list);
        testResults = this.getFormattedTestResults(list);
        params.put("testsummary", testSummary);
        params.put("testresults", testResults);
        TestReportGenerator.generateReport(params);
    }

    private ArrayList<ITestResult> listTestResult(IResultMap resultMap) {
        Set<ITestResult> results = resultMap.getAllResults();
        return new ArrayList<ITestResult>(results);
    }

    private void sort(List<ITestResult> list) {
        Collections.sort(list, new Comparator<ITestResult>() {
            //@Override
            public int compare(ITestResult r1, ITestResult r2) {
                if (r1.getStartMillis() > r2.getStartMillis()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    private void outputResult(List<ITestResult> list, String path) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(new File(path)));
            StringBuffer sb = new StringBuffer();

            Map<String, Object> params = new HashMap<String, Object>();

            for (ITestResult result :
                    list) {
                if (sb.length() != 0) {
                    sb.append("\r\n");
                }

                sb.append(result.getTestClass().getRealClass().getName())
                        .append("")
                        .append(result.getMethod().getMethodName())
                        .append("")
                        .append(this.formatDate(result.getStartMillis()))
                        .append("")
                        .append(result.getEndMillis() - result.getStartMillis())
                        .append("毫秒")
                        .append(this.getStatus(result.getStatus()))
                        .append("")
                        .append(
                                result.getThrowable() == null ? "" : StringUtils.join(result.getThrowable().getStackTrace()));
            }
            output.write(sb.toString());
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取执行结果（int->String类型）
     *
     * @return
     * @paramstatus
     */
    private String getStatus(int status) {
        String statusString = null;
        switch (status) {
            case 1:
                statusString = "SUCCESS";
                break;
            case 2:
                statusString = "FAILURE";
                break;
            case 3:
                statusString = "SKIP";
                break;

            default:
                break;
        }
        return statusString;
    }

    /**
     * 根据测试执行结果统计相应的total,pass,fail,skip的用例
     *
     * @return
     * @paramtestResults
     */
    private TestSummary getTestSummary(List<ITestResult> testResults) {
        TestSummary summary = new TestSummary();
        if (testResults.size() > 0) {
            summary.total = testResults.size();
            for (ITestResult result :
                    testResults) {
                int status = result.getStatus();
                switch (status) {
                    case 1:
                        summary.pass += 1;
                        break;
                    case 2:
                        summary.fail += 1;
                        break;
                    case 3:
                        summary.skip += 1;
                        break;
                    default:
                        break;
                }
            }
        }
        return summary;
    }

    /**
     * 获取格式化的测试结果，作为freemarker的输入源
     *
     * @return
     * @paramtestResults
     */
    private List<UITestResult> getFormattedTestResults(List<ITestResult> testResults) {
        List<UITestResult> results = new ArrayList<UITestResult>();

        if (testResults.size() > 0) {
            for (ITestResult result : testResults) {
                UITestResult temp = new UITestResult();
                temp.setClassName(result.getTestClass().getRealClass().getName());
                temp.setMethodName(result.getMethod().getMethodName());
                temp.setStartTime(this.formatDate(result.getStartMillis()));
                temp.setDuration(String.valueOf(result.getEndMillis() - result.getStartMillis()));
                temp.setResult(this.getStatus(result.getStatus()));
                temp.setException(
                        result.getThrowable() == null ? "" : StringUtils.join(result.getThrowable().getStackTrace()));
                results.add(temp);
            }
        }
        return results;
    }

    /**
     * 时间格式化
     *
     * @return
     * @paramdate
     */
    private String formatDate(long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        return formatter.format(date);
    }
}

