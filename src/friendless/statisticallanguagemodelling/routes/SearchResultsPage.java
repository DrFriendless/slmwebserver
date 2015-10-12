package friendless.statisticallanguagemodelling.routes;

import friendless.statisticallanguagemodelling.Corpus;
import friendless.statisticallanguagemodelling.Icons;
import friendless.statisticallanguagemodelling.Parameters;
import friendless.statisticallanguagemodelling.WithCorpusRoute;
import friendless.statisticallanguagemodelling.query.*;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 29/08/15.
 */
public class SearchResultsPage extends WithCorpusRoute {
    @Override
    public Object handle(Request request, Response response, Corpus corpus) throws Exception {
        if (request.splat().length != 1) {
            response.status(400);
            return null;
        }
        String splat = request.splat()[0];
        String[] fields = splat.split("/");
        if (fields.length != 2) {
            response.status(400);
            return null;
        }
        String[] words = fields[1].split(",");
        List<Integer> searchWords = new ArrayList<>();
        for (int i=0; i<words.length; i++) {
            words[i] = words[i].trim();
            try {
                searchWords.add(Integer.parseInt(words[i]));
            } catch (NumberFormatException ex) {
                response.status(400);
                return null;
            }
        }
        Query q;
        if ("all".equals(fields[0])) {
            q = new AndQuery(searchWords);
        } else if ("any".equals(fields[0])) {
            q = new AnyQuery(searchWords);
        } else if ("best".equals(fields[0])) {
            q = new BestQuery(searchWords, corpus);
        } else {
            response.status(400);
            return null;
        }
        List<List<Integer>> results = q.search(corpus);
        if (results.size() > Parameters.MAX_RESULTS) {
            results = results.subList(0, Parameters.MAX_RESULTS);
        }
        Map<String, Object> context = new HashMap<>();
        context.put("results", Icons.convertStories(results));
        context.put("id", corpus.getID());
        return respond("templates/searchResult.html", context);
    }
}
