package result.strategy;

import enums.SortOrderType;
import model.Document;

import java.util.Comparator;
import java.util.List;

public class RecentlyPublishedOrder implements ResultOrderStrategy {
    @Override
    public List<Document> sort(List<Document> documents, SortOrderType sortOrderType) {
        if (sortOrderType == SortOrderType.ASC) {
            documents.sort(Comparator.comparing(Document::getPublishDate));
        } else {
            documents.sort((doc1, doc2) -> doc2.getPublishDate().compareTo(doc1.getPublishDate()));
        }
        return documents;
    }
}