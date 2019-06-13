package com.stackroute.articleRecommendationService.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Source {

    @Id
    private int sourceId;

    @JsonProperty("name")
    private String sourceName;

    public Source() {
    }

    public Source(int sourceId, String sourceName) {
        this.sourceId = sourceId;
        this.sourceName = sourceName;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public String toString() {
        return "Source{" +
                "sourceId=" + sourceId +
                ", sourceName='" + sourceName + '\'' +
                '}';
    }
}
