package search.factory;

import enums.SearchType;
import search.strategy.OrderedSearch;
import search.strategy.SearchStrategy;
import search.strategy.SubstringSearch;
import search.strategy.UnorderedSearch;

public class SearchFactory {
    public static SearchStrategy createSearchStrategy(SearchType type) {
        return switch (type) {
            case UNORDERED -> new UnorderedSearch();
            case ORDERED -> new OrderedSearch();
            case SUBSTRING -> new SubstringSearch();
            default -> null;
        };
    }
}