package friendless.statisticallanguagemodelling.query;

import java.util.Collections;
import java.util.List;

/**
 * Created by john on 11/09/15.
 */
public class AnyQuery extends AbstractMatchingQuery {
    private List<Integer> words;

    public AnyQuery(List<Integer> words) {
        this.words = words;
    }

    @Override
    public boolean match(List<Integer> story) {
        return !Collections.disjoint(words, story);
    }
}
