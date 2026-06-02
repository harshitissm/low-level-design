package ranking;

import model.SearchResult;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyRankingStrategy implements RankingStrategy {

    @Override
    public List<SearchResult> rank(Map<String, Integer> scores) {
        return scores.entrySet()
                .stream()
                .map(entry -> new SearchResult(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(SearchResult::getScore).reversed())
                .collect(Collectors.toList());
    }
}