package listeners;

/**
 * 测试结果统计类，包括用例执行总数、通过个数、失败个数和忽略个数
 * Created by xualvin on 7/7/18.
 */
public class TestSummary {
    public int total;
    public int pass;
    public int fail;
    public int skip;

    public TestSummary() {
    }

    public TestSummary(int total, int pass, int fail, int skip) {
        this.total = total;
        this.pass = pass;
        this.fail = fail;
        this.skip = skip;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    @Override
    public String toString() {
        return "TestSummary{" +
                "total=" + total +
                ", pass=" + pass +
                ", fail=" + fail +
                ", skip=" + skip +
                '}';
    }
}
