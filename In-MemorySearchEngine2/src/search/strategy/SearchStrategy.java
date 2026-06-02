package search.strategy;

import model.Category;
import model.Document;

import java.util.List;

public interface SearchStrategy {
    public List<Document> search(Category category, String pattern);
}

