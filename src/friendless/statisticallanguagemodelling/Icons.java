package friendless.statisticallanguagemodelling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by john on 25/08/15.
 */
public class Icons {
    public static List<String> getAllIcons() {
        Collection<String> filenames = ResourceList.getResources(Pattern.compile(".*.svg"));
        List<String> result = filenames.
                stream().
                filter(s -> s.contains("public/icons")).
                map(s -> "/icons" + s.substring(s.lastIndexOf('/'))).
                collect(Collectors.toList());
        Collections.sort(result);
        return result;
    }

    public static final int GROUP_SIZE = 4;

    public static List<List<String>> getIconArray() {
        List<String> names = getAllIcons();
        List<List<String>> result = new ArrayList<>();
        while (names.size() > 0) {
            if (names.size() > GROUP_SIZE) {
                result.add(new ArrayList<>(names.subList(0, GROUP_SIZE)));
                names = names.subList(GROUP_SIZE, names.size());
            } else {
                result.add(new ArrayList<>(names));
                names.clear();
            }
        }
        return result;
    }

    public static List<List<String>> convertStories(List<List<Integer>> input) {
        List<String> allIcons = getAllIcons();
        List<List<String>> stories = new ArrayList<>();
        for (List<Integer> ints : input) {
            List<String> story = ints.stream().map(i -> allIcons.get(i)).collect(Collectors.toList());
            stories.add(story);
        }
        return stories;
    }
}
