package service;

import dataset.Dataset;
import model.SearchResult;
import ranking.RankingStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEngine {

    private final Map<String, Dataset> datasets = new HashMap<>();

    public void createDataset(String datasetName) {
        datasets.putIfAbsent(datasetName, new Dataset(datasetName));
    }

    public void insertDocument(String datasetName, String documentId, String content) {
        getDataset(datasetName).insertDocument(documentId, content);
    }

    public void deleteDocument(String datasetName, String documentId) {
        getDataset(datasetName).deleteDocument(documentId);
    }

    public List<SearchResult> search(String datasetName, String query, RankingStrategy strategy) {
        return getDataset(datasetName).search(query, strategy);
    }

    private Dataset getDataset(String datasetName) {
        Dataset dataset = datasets.get(datasetName);
        if (dataset == null) {
            throw new IllegalArgumentException("Dataset not found");
        }
        return dataset;
    }
}