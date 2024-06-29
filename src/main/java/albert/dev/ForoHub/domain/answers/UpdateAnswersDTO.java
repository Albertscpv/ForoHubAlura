package albert.dev.ForoHub.domain.answers;

public record UpdateAnswersDTO(
        Long Id,
        String message_to_answer,
        String answer,
        String creation_date
) {
}
