package friendless.statisticallanguagemodelling;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import spark.Route;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * Created by john on 29/08/15.
 */
public abstract class VeocityEnabledRoute implements Route {
    private static void initVelocity() throws IOException {
        Properties props = new Properties();
        props.load(WebServer.class.getClassLoader().getResourceAsStream("velocity.properties"));
        Velocity.init(props);
    }

    protected String respond(String template, Map<String, Object> params) throws IOException {
        initVelocity();
        VelocityContext context = new VelocityContext(params);
        StringWriter w = new StringWriter();
        Velocity.mergeTemplate(template, context, w);
        return w.toString();
    }
}
