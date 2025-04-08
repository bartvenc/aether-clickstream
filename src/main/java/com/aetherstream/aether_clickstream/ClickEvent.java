package com.aetherstream.aether_clickstream;

public class ClickEvent {
    private Long itemId;
    private String itemType;
    private Long timestamp;

    // Default constructor
    public ClickEvent() {}

    // Parameterized constructor
    public ClickEvent(Long itemId, String itemType, Long timestamp) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.timestamp = timestamp;
    }

    // Getters and setters for each field:
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public String getItemType() {
        return itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public Long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}