package friendless.statisticallanguagemodelling.routes;

import friendless.statisticallanguagemodelling.Icons;
import friendless.statisticallanguagemodelling.VeocityEnabledRoute;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 29/08/15.
 */
public class FrontPage extends VeocityEnabledRoute {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            Map<String, Object> context = new HashMap<>();
            context.put("icons", Icons.getIconArray());
            return respond("templates/new.html", context);
        }
}
