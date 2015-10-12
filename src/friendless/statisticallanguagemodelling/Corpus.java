package friendless.statisticallanguagemodelling;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by john on 25/08/15.
 */
public class Corpus {
    private int index;
    private String passphrase;
    private Date creationDate;
    private List<List<Integer>> stories;

    public Corpus(int index, String passphrase, Date creationDate) {
        this.index = index;
        this.passphrase = passphrase;
        this.creationDate = creationDate;
        this.stories = new ArrayList<>();
    }

    public void addStory(List<Integer> story) {
        stories.add(story);
    }

    @Override
    public String toString() {
        return "Corpus[" + index + " " + creationDate + "]";
    }

    public List<List<Integer>> getStories() {
        return stories;
    }

    public Map<String, Object> getStatistics() {
        Map<Integer, AtomicInteger> wordCounts = new HashMap<>();
        for (int w = 0; w<Parameters.HIGHEST_WORD; w++) {
            wordCounts.put(w, new AtomicInteger(0));
        }
        int totalWords = 0;
        for (List<Integer> story : stories) {
            for (int w : story) {
                wordCounts.get(w).incrementAndGet();
                totalWords++;
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("documentCount", stories.size());
        result.put("wordCounts", wordCounts);
        result.put("totalWords", totalWords);
        return result;
    }

    public int getID() {
        return index;
    }
}
