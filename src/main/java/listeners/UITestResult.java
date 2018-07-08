package listeners;

/**
 * 记录UI自动化测试结果详细信息
 * Created by xualvin on 7/7/18.
 */
public class UITestResult {
    public String className;
    public String methodName;
    public String startTime;
    public String duration;
    public String result;
    public String exception;

    public UITestResult() {
    }

    public UITestResult(String className, String methodName, String startTime, String duration, String result, String exception) {
        this.className = className;
        this.methodName = methodName;
        this.startTime = startTime;
        this.duration = duration;
        this.result = result;
        this.exception = exception;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "UITestResult{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", duration='" + duration + '\'' +
                ", result='" + result + '\'' +
                ", exception='" + exception + '\'' +
                '}';
    }
}
