package friendless.statisticallanguagemodelling.query;

import friendless.statisticallanguagemodelling.Corpus;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by john on 11/09/15.
 */
public class BestQuery extends EvaluatingQuery {
    private List<Integer> words;
    private Corpus corpus;
    Map<Integer, Double> wordValues;

    public BestQuery(List<Integer> words, Corpus corpus) {
        this.words = words;
        this.corpus = corpus;
    }

    private void buildLanguageModel() {
        // count the number of occurrences of each word.
        int total = 0;
        Map<Integer, Integer> wordCount = new HashMap<>();
        for (List<Integer> story : corpus.getStories()) {
            for (Integer word : story) {
                total++;
                if (wordCount.get(word) == null) wordCount.put(word, 0);
                wordCount.put(word, wordCount.get(word) + 1);
            }
        }
        wordValues = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : wordCount.entrySet()) {
            wordValues.put(entry.getKey(), total * 1.0 / entry.getValue());
        }
    }

    private double score(List<Integer> story) {
        double score = 1.0 / story.size();
        for (Integer word : words) {
            if (story.contains(word) && wordValues.containsKey(word)) score *= wordValues.get(word);
        }
        return score;
    }

    @Override
    public List<List<Integer>> search(Corpus corpus) {
        buildLanguageModel();
        return corpus.getStories().
                stream().
                map(story -> new ScoredStory(story, score(story))).
                sorted().
                map(ss -> ss.story).
                collect(Collectors.toList());
    }

    static class ScoredStory implements Comparable<ScoredStory> {
        private double score;
        private List<Integer> story;

        ScoredStory(List<Integer> story, double score) {
            this.score = score;
            this.story = story;
        }

        @Override
        public int compareTo(ScoredStory o) {
            return -Double.compare(score, o.score);
        }
    }
}
