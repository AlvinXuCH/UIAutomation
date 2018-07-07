package testrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import utils.PropertyUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 所有testng测试类的基类，用来存放公共变量和方法
 * 包括：testng中setup和teardown的公共实现，各自类可以重写
 * Created by xualvin on 7/7/18.
 */
public class BaseTest {
    //selenium web driver
    protected WebDriver driver;

    /**
     * test方法执行前的准备工作
     */
    @BeforeMethod
    public void setUp(){
        Properties properties = PropertyUtils.getAll("application.properties");
        String runLocally = properties.getProperty("runlocally");
        //是否设置本地执行 1-本地执行， 0-selenium grid执行，此时需要提供selenium grid的hub链接
        if("1".equals(runLocally)){
            driver = new ChromeDriver();
        }else {
            String hub = properties.getProperty("hub");
            ChromeOptions options = new ChromeOptions();
            try {
                driver = new RemoteWebDriver(new URL(hub),options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * test方法执行后的操作
     * 这里可通过ITestResult获取当前test方法执行后的结果
     */
    @AfterMethod
    public void tearDown(ITestResult testResult){
        driver.close();
    }

    /**
     * 测试集执行完后的操作
     * TODO 测试集执行后添加一写生成报表，发送通知或者邮件等
     */
    @AfterSuite
    protected void afterSuite(){

    }

}
