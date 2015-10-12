package friendless.statisticallanguagemodelling.query;

import friendless.statisticallanguagemodelling.Corpus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 29/08/15.
 */
public abstract class AbstractMatchingQuery implements Query {
    public abstract boolean match(List<Integer> story);

    public List<List<Integer>> search(Corpus corpus) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> story : corpus.getStories()) {
            if (match(story)) {
                result.add(story);
            }
        }
        return result;
    }
}
