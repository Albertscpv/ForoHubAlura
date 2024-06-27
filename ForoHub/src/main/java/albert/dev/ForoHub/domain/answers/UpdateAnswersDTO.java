package albert.dev.ForoHub.domain.answers;

public record UpdateAnswersDTO(
        Long Id,
        String messageToAnswer,
        String answer,
        String creation_date
) {
}
