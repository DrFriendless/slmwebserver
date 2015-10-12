package friendless.statisticallanguagemodelling.routes;

import com.google.gson.Gson;
import friendless.statisticallanguagemodelling.Corpus;
import friendless.statisticallanguagemodelling.Icons;
import friendless.statisticallanguagemodelling.WithCorpusRoute;
import spark.Request;
import spark.Response;

import java.util.List;

/**
 * Created by john on 29/08/15.
 */
public class RetrieveJsonCorpusWriteData extends WithCorpusRoute {
    @Override
    public Object handle(Request request, Response response, Corpus c) throws Exception {
        Gson gson = new Gson();
        List<String> icons = Icons.getAllIcons();
        return gson.toJson(icons);
    }
}
