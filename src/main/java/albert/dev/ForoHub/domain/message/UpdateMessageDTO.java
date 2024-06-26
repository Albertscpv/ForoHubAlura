package albert.dev.ForoHub.domain.message;

import jakarta.validation.constraints.NotNull;

public record UpdateMessageDTO(
        @NotNull
        Long Id,

        String message,
        String topic,
        String creation_date
) {

}
