package co.istad.istademy.api.feedback.web;

import co.istad.istademy.api.user.User;

import java.sql.Timestamp;

public record FeedBackDto(
        User userId,
        String uuid,
        String contents,
        Timestamp createdAt
) {

}
