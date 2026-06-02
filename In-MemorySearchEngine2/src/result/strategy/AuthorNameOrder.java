package result.strategy;

import enums.SortOrderType;
import model.Document;

import java.util.Collections;
import java.util.List;

public class AuthorNameOrder implements ResultOrderStrategy {
    @Override
    public List<Document> sort(List<Document> documents, SortOrderType sortOrderType) {
        return Collections.emptyList();
    }
}
