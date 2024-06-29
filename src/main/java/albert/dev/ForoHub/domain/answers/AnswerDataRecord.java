package albert.dev.ForoHub.domain.answers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerDataRecord(
        @NotBlank
        String message_to_answer,
        @NotBlank
        String user,
        @NotBlank
        String topic,
        @NotNull
        String creation_date,
        @NotBlank
        String answer
) {
}
