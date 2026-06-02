package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Document> documents;
    private InvertedIndex invertedIndex;

    public Category(String name) {
        this.name = name;
        this.documents = new ArrayList<>();
        this.invertedIndex = new InvertedIndex();
    }

    public void addDocument(Document document) {
        documents.add(document);
        updateInvertedIndex(document);
    }

    public void updateInvertedIndex(Document document) {
        invertedIndex.addDocument(document);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public InvertedIndex getInvertedIndex() {
        return invertedIndex;
    }

    public void setInvertedIndex(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }
}
