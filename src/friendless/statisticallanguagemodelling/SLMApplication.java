package friendless.statisticallanguagemodelling;

import spark.servlet.SparkApplication;

/**
 * Created by john on 22/09/15.
 */
public class SLMApplication implements SparkApplication {
    @Override
    public void init() {
        WebServer.main(null);
    }
}
