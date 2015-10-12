package friendless.statisticallanguagemodelling.query;

import java.util.List;

/**
 * Created by john on 29/08/15.
 */
public class NoneQuery extends AbstractMatchingQuery {
    @Override
    public boolean match(List<Integer> story) {
        return false;
    }
}
