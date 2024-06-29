package albert.dev.ForoHub.domain.answers;

public record AnswerDataResponse(
        Long Id,
        String message_to_answer,
        String user,
        String topic,
        String creation_date,
        Boolean online
) {
}
