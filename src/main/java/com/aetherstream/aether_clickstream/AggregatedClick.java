package com.aetherstream.aether_clickstream;

public class AggregatedClick {
    private Long itemId;
    private String itemType;
    private Long clickCount;

    public AggregatedClick() {}

    public AggregatedClick(Long itemId, String itemType, Long clickCount, String week) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.clickCount = clickCount;
    }

    // Getters and setters
    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }

    public Long getClickCount() { return clickCount; }
    public void setClickCount(Long clickCount) { this.clickCount = clickCount; }

}