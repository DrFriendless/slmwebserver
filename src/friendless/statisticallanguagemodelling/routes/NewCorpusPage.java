package friendless.statisticallanguagemodelling.routes;

import friendless.statisticallanguagemodelling.Database;
import friendless.statisticallanguagemodelling.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by john on 29/08/15.
 */
public class NewCorpusPage implements Route {
    private static final Logger LOG = LoggerFactory.getLogger(WebServer.class);

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String passphrase = "Passphrase not used";
        Database db = new Database();
        int id = db.createNewCorpus(passphrase);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Corpus {} created", id);
        }
        response.redirect("/corpus/" + id);
        return null;
    }
}
