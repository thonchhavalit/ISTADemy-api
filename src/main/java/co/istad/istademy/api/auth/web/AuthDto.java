package co.istad.istademy.api.auth.web;

public record AuthDto(String tokenType,
                      String accessToken,
                      String refreshToken) {
}
