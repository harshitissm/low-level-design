package index;

import model.Document;

import java.util.*;

public class InvertedIndex {

    /*
      apple ->
             doc1 -> 1
             doc2 -> 2
     */
    private final Map<String, Map<String, Integer>> index = new HashMap<>();

    public void addDocument(Document document) {
        List<String> tokens = tokenize(document.getContent());

        for (String token : tokens) {
            Map<String, Integer> postingList = index.computeIfAbsent(token, k -> new HashMap<>());
            postingList.merge(document.getId(), 1, Integer::sum);
        }
    }

    public void removeDocument(Document document) {
        List<String> tokens = tokenize(document.getContent());

        for (String token : tokens) {
            Map<String, Integer> postingList = index.get(token);

            if (postingList == null) {
                continue;
            }

            postingList.remove(document.getId());

            if (postingList.isEmpty()) {
                index.remove(token);
            }
        }
    }

    public Map<String, Integer> search(List<String> terms) {
        Map<String, Integer> scores = new HashMap<>();

        for (String term : terms) {

            Map<String, Integer> postingList = index.get(term);

            if (postingList == null) {
                continue;
            }

            for (Map.Entry<String, Integer> entry : postingList.entrySet()) {
                scores.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        return scores;
    }

    private List<String> tokenize(String text) {
        String[] words = text.toLowerCase().split("[^a-z0-9]+");

        return Arrays.stream(words)
                .filter(word -> !word.isBlank())
                .toList();
    }
}