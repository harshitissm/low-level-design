package result.strategy;

import enums.SortOrderType;
import model.Document;

import java.util.List;

public interface ResultOrderStrategy {
    public List<Document> sort(List<Document> documents, SortOrderType sortOrderType);
}
