package search.strategy;

import model.Category;
import model.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UnorderedSearch implements SearchStrategy {
    @Override
    public List<Document> search(Category category, String pattern) {
        String[] words = pattern.toLowerCase().split(" ");
        Set<Document> documents = category.getInvertedIndex().getDocumentsForWord(words[0]);
        for (int i = 1; i < words.length; i++) {
            documents.retainAll(category.getInvertedIndex().getDocumentsForWord(words[i]));
        }
        return new ArrayList<>(documents);
    }
}
