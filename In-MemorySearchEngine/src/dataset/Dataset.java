package dataset;

import index.InvertedIndex;
import model.Document;
import model.SearchResult;
import ranking.RankingStrategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dataset {

    private final String name;
    private final Map<String, Document> documents = new HashMap<>();
    private final InvertedIndex index = new InvertedIndex();

    public Dataset(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void insertDocument(String documentId, String content) {
        if (documents.containsKey(documentId)) {
            throw new IllegalArgumentException("Document already exists");
        }
        Document document = new Document(documentId, content);
        documents.put(documentId, document);
        index.addDocument(document);
    }

    public void deleteDocument(String documentId) {
        Document document = documents.remove(documentId);

        if (document == null) {
            return;
        }

        index.removeDocument(document);
    }

    public List<SearchResult> search(String query, RankingStrategy rankingStrategy) {
        List<String> terms = tokenize(query);
        Map<String, Integer> scores = index.search(terms);
        return rankingStrategy.rank(scores);
    }

    private List<String> tokenize(String query) {
        return Arrays.stream(query.toLowerCase().split("[^a-z0-9]+"))
                .filter(token -> !token.isBlank())
                .toList();
    }
}