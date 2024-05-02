package co.istad.istademy.api.image.web;

import java.sql.Timestamp;

public record ImageDto(
        String uuid,
        String name,
        String url,
        Timestamp createdAt,
        Boolean isDisabled
) {
}
