package friendless.statisticallanguagemodelling.query;

import java.util.List;

/**
 * Created by john on 29/08/15.
 */
public class AndQuery extends AbstractMatchingQuery {
    private List<Integer> words;

    public AndQuery(List<Integer> words) {
        this.words = words;
    }

    @Override
    public boolean match(List<Integer> story) {
        return story.containsAll(words);
    }
}
