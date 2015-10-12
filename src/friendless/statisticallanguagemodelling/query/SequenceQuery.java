package friendless.statisticallanguagemodelling.query;

import java.util.Collections;
import java.util.List;

/**
 * Created by john on 29/08/15.
 */
public class SequenceQuery extends AbstractMatchingQuery {
    private List<Integer> query;

    public SequenceQuery(List<Integer> query) {
        this.query = query;
    }

    @Override
    public boolean match(List<Integer> story) {
        return Collections.indexOfSubList(story, query) >= 0;
    }
}
