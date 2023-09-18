package org.example.request;

import org.example.model.Item;

import java.util.UUID;

public record RequestTrade(UUID sourceInventoryId, Item sourceTradeItem, UUID targetInventoryId, Item targetTradeItem) {
}
