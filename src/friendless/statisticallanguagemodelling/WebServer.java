package friendless.statisticallanguagemodelling;

import friendless.statisticallanguagemodelling.routes.*;

import static spark.Spark.*;

/**
 * Created by john on 25/08/15.
 */
public class WebServer {
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/create", new NewCorpusPage());
        get("/admin/:id", new AdminPage());
        get("/search/:id", new SearchPage());
        get("/query/:id/*", new SearchResultsPage());
        get("/corpus/:id","application/html",  new ShowCorpusPage());
        get("/write/:id", "application/json", new RetrieveJsonCorpusWriteData());
        get("/write/:id", "application/html", new RetrieveJsonCorpusWriteData()); // for debugging
        post("/story/:id", new CreateStoryAction());
        get("/story/:id", new CreateStoryAction()); // http://127.0.0.1:4567/story/903927?story=4,5,7,12,13,1
        post("/delete/:id", new DeleteCorpusAction());
        get("/", new FrontPage());
    }
}
