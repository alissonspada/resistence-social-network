package org.example.request;

import java.util.UUID;

public record RequestReport(UUID sourceId, UUID targetId) {
}
