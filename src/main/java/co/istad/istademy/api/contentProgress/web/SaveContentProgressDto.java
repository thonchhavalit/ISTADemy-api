package co.istad.istademy.api.contentProgress.web;

import jakarta.validation.constraints.NotNull;

public record SaveContentProgressDto(
        @NotNull(message = "Content is required!!!")
        Integer contentId,
        @NotNull(message = "User is required!!!!")
        Integer userId,

        Integer courseId
) {

}
