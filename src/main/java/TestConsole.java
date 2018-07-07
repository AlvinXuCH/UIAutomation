import utils.PropertyUtils;

import java.util.Properties;

/**
 * Created by xualvin on 7/7/18.
 */
public class TestConsole {
    public static void main(String[] args){
        System.out.println("hello world");


        int addresult = PropertyUtils.addProperty("","test","11","add");
        System.out.println(addresult);
        String value = PropertyUtils.getValueByKey("application.properties","hub");
        System.out.println(value);

        Properties properties = PropertyUtils.getAll("application.properties");
        System.out.println(properties.toString());

    }
}
