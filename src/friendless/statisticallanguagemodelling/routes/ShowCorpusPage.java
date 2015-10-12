package friendless.statisticallanguagemodelling.routes;

import friendless.statisticallanguagemodelling.Corpus;
import friendless.statisticallanguagemodelling.Icons;
import friendless.statisticallanguagemodelling.WithCorpusRoute;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 29/08/15.
 */
public class ShowCorpusPage extends WithCorpusRoute {
        @Override
        public Object handle(Request request, Response response, Corpus c) throws Exception {
            Map<String, Object> context = new HashMap<>();
            context.put("id", c.getID());
            context.put("stories", Icons.convertStories(c.getStories()));
            context.putAll(c.getStatistics());
            return respond("templates/corpus.html", context);
    }
}
