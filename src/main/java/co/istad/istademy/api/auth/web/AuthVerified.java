package co.istad.istademy.api.auth.web;

public record AuthVerified(
        String email,
        String verifiedCode
) {
}
