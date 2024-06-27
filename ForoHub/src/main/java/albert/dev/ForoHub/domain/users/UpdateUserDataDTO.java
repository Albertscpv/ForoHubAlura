package albert.dev.ForoHub.domain.users;

import jakarta.validation.constraints.NotNull;

public record UpdateUserDataDTO(
        @NotNull
        Long Id,

        String name,
        String email,
        String password){
}
