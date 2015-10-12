package friendless.statisticallanguagemodelling.query;

import friendless.statisticallanguagemodelling.Corpus;

import java.util.List;

/**
 * Created by john on 15/09/15.
 */
public interface Query {
    public List<List<Integer>> search(Corpus corpus);
}
