package friendless.statisticallanguagemodelling.routes;

import friendless.statisticallanguagemodelling.Corpus;
import friendless.statisticallanguagemodelling.Database;
import friendless.statisticallanguagemodelling.Parameters;
import friendless.statisticallanguagemodelling.WithCorpusRoute;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 29/08/15.
 */
public class CreateStoryAction extends WithCorpusRoute {
    @Override
    public Object handle(Request request, Response response, Corpus c) throws Exception {
        String story = request.queryParams("story");
        String[] words = story.split(",");
        List<Integer> intStory = new ArrayList<>();
        for (String s : words) {
            s = s.trim();
            try {
                int i = Integer.parseInt(s);
                if (i < 0 || i >= Parameters.HIGHEST_WORD) continue;
                intStory.add(i);
            } catch (NumberFormatException ex) {
                continue;
            }
            if (intStory.size() > Parameters.LONGEST_STORY) break;
        }
        if (intStory.size() == 0) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
            return "Story too short";
        }
        Database db = new Database();
        db.addStory(c.getID(), intStory);
        return c;
    }
}
