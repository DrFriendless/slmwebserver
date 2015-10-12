package friendless.statisticallanguagemodelling.routes;

import friendless.statisticallanguagemodelling.Corpus;
import friendless.statisticallanguagemodelling.WithCorpusRoute;
import spark.Request;
import spark.Response;

/**
 * Created by john on 15/09/15.
 */
public class AdminPage extends WithCorpusRoute {
    @Override
    public Object handle(Request request, Response response, Corpus c) throws Exception {
        return null;
    }
}
