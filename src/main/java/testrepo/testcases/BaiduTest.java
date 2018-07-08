package testrepo.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.testpage.BaiduPage;
import testrepo.BaseTest;

/**
 * Created by xualvin on 8/7/18.
 */
public class BaiduTest extends BaseTest {
    @Test
    public void testBaidu(){
        BaiduPage baiduPage = new BaiduPage(driver);
        baiduPage.driver.get("http://www.baidu.com");
        baiduPage.sleep(2);
        Assert.assertTrue(true);
    }
}
