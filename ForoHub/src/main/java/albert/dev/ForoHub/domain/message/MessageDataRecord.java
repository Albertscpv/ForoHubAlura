package albert.dev.ForoHub.domain.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MessageDataRecord(

        @NotBlank
        String user,

        @NotBlank
        String topic,

        @NotBlank
        String message,

        @NotNull
        String creation_date
) {
}
