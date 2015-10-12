package friendless.statisticallanguagemodelling.routes;

import friendless.statisticallanguagemodelling.Corpus;
import friendless.statisticallanguagemodelling.Icons;
import friendless.statisticallanguagemodelling.Parameters;
import friendless.statisticallanguagemodelling.WithCorpusRoute;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 29/08/15.
 */
public class SearchPage extends WithCorpusRoute {
    @Override
    public Object handle(Request request, Response response, Corpus c) throws Exception {
        Map<String, Object> context = new HashMap<>();
        context.put("id", c.getID());
        context.put("stories", c.getStories());
        List<String> icons = Icons.getAllIcons();
        context.put("icons", icons);
        context.put("searchMax", Parameters.LONGEST_STORY);
        context.put("longestStory", Parameters.LONGEST_STORY);
        context.put("resultMax", Parameters.MAX_RESULTS);
        return respond("templates/search.html", context);
    }
}
