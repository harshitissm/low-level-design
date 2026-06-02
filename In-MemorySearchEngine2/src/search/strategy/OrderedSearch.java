package search.strategy;

import model.Category;
import model.Document;

import java.util.Collections;
import java.util.List;

public class OrderedSearch implements SearchStrategy {
    @Override
    public List<Document> search(Category category, String pattern) {
        return Collections.emptyList();
    }
}
