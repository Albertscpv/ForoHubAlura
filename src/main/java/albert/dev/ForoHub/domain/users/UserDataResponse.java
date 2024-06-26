package albert.dev.ForoHub.domain.users;

public record UserDataResponse(
         Long Id,
         String name,
         String cellphone,
         String email,
         String country,
         String password,
         Boolean online
) {
}
