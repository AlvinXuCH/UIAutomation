package pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 所有页面类的基类，定义和封装页面的公共操作方法
 * 每个具体页面类需要继承该类
 * Created by xualvin on 7/7/18.
 */
public class BasePage {

    //selenium web driver
    public WebDriver driver;

    //用于记录前一浏览器窗口的句柄，做窗口切换使用
    public static String windowHandleBefore = StringUtils.EMPTY;

    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        //设置元素查找默认等待时间为10秒
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * 切换到新打开的窗口
     * TODO 现在只能处理两个窗口的情况，如果当前窗口>2，有可能切换到错误窗口
     * @return
     */
    public WebDriver switchToNewWindow() {
        WebDriver newDriver = null;
        windowHandleBefore = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(windowHandleBefore)) {
                newDriver = targetLocator().window(windowHandle);
            }
        }
        return newDriver;
    }

    /**
     * 切换回原来浏览器窗口，并将原来窗口句柄记录清空，如果有需要再使用的例子可以单独做特殊处理
     * @return
     */
    public WebDriver switchWindowBack(){
        WebDriver beforeDriver = null;
        if(!StringUtils.isEmpty(windowHandleBefore)){
            beforeDriver = targetLocator().window(windowHandleBefore);
            windowHandleBefore = "";
        }
        return beforeDriver;
    }

    private TargetLocator targetLocator() {
        return driver.switchTo();
    }

    /**
     * sleep 操作
     * @param seconds
     */
    public void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待元素出现（timeout 10s，每2s查看一次）
     * @param element
     */
    public void waitForVisibility(WebElement element){
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(java.util.NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * 等待元素消失（timeout 10s，每2s查看一次）
     * @param element
     */
    public void waitForInvisibility(WebElement element){
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(java.util.NoSuchElementException.class);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
