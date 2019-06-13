package com.stackroute.rabbitmq.domain;



public class SourceDTO {


    private int sourceId;
    private String sourceName;

    public SourceDTO() {
    }

    public SourceDTO(int sourceId, String sourceName) {
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
        return "SourceDTO{" +
                "sourceId=" + sourceId +
                ", sourceName='" + sourceName + '\'' +
                '}';
    }
}
