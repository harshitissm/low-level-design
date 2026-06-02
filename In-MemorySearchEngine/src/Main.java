import model.SearchResult;
import ranking.FrequencyRankingStrategy;
import ranking.RankingStrategy;
import service.SearchEngine;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        SearchEngine engine = new SearchEngine();

        engine.createDataset("tech");

        engine.insertDocument("tech", "doc1", "apple is a fruit");

        engine.insertDocument("tech", "doc2", "apple apple come on");

        engine.insertDocument("tech", "doc3", "oranges are sour");

        engine.insertDocument("tech", "doc4", "apple-pie is sweet");

        RankingStrategy ranking = new FrequencyRankingStrategy();

        List<SearchResult> results = engine.search("tech","apple", ranking);

        results.forEach(System.out::println);
    }
}