package listeners;

import freemarker.template.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

/**
 * 通过Freemarker生成测试报告
 * Created by xualvin on 8/7/18.
 */
public class TestReportGenerator {

    /**
     * Freemarker根据提供的数据生成报表
     * 其中：params更加模板ftl的情况填写，可以为空
     * @param params
     * @return
     */
    public static int generateReport(Map<String, Object> params) {
        //Freemarker相关配置
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setClassForTemplateLoading(TestReportGenerator.class, "/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.CHINA);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template template = null;
        try {
            template = configuration.getTemplate("/report.ftl");
            Writer consoleWriter = new OutputStreamWriter(System.out);
            template.process(params, consoleWriter);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
