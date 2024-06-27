package albert.dev.ForoHub.domain.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDataRecord(
        @NotBlank
        String name,
        @NotNull
        @Pattern(regexp = "\\d{8,15}")
        String cellphone,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String country,
        @NotNull
        String password
) {
}
