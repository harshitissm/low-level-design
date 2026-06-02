package search.strategy;

import model.Category;
import model.Document;

import java.util.List;
import java.util.stream.Collectors;

public class SubstringSearch implements SearchStrategy {
    @Override
    public List<Document> search(Category category, String pattern) {
        return category.getDocuments().stream()
                .filter(doc -> doc.getContent().toLowerCase().contains(pattern.toLowerCase()))
                .collect(Collectors.toList());
    }
}
