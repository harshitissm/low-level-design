package model;

public class SearchResult {

    private final String documentId;
    private final int score;

    public SearchResult(String documentId, int score) {
        this.documentId = documentId;
        this.score = score;
    }

    public String getDocumentId() {
        return documentId;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return documentId + " (" + score + ")";
    }
}