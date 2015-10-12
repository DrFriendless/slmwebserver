package friendless.statisticallanguagemodelling.routes;

import friendless.statisticallanguagemodelling.Corpus;
import friendless.statisticallanguagemodelling.Database;
import friendless.statisticallanguagemodelling.WithCorpusRoute;
import spark.Request;
import spark.Response;

/**
 * Created by john on 15/09/15.
 */
public class DeleteCorpusAction extends WithCorpusRoute {
    @Override
    public Object handle(Request request, Response response, Corpus c) throws Exception {
        Database db = new Database();
        db.deleteCorpus(c.getID());
        response.redirect("/create");
        return null;
    }
}
