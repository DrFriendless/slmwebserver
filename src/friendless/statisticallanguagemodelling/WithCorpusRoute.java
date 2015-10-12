package friendless.statisticallanguagemodelling;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by john on 29/08/15.
 */
public abstract class WithCorpusRoute extends VeocityEnabledRoute {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        int id = Integer.parseInt(request.params("id"));
        Database db = new Database();
        try {
            Corpus c = db.retrieveCorpus(id);
            return handle(request, response, c);
        } catch (NotFoundException ex) {
            response.status(404);
            return "No such corpus";
        }
    }

    public abstract Object handle(Request request, Response response, Corpus c) throws Exception;
}
