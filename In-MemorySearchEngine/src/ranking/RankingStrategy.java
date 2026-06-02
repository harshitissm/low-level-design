package ranking;

import model.SearchResult;

import java.util.List;
import java.util.Map;

public interface RankingStrategy {
    List<SearchResult> rank(Map<String, Integer> scores);
}