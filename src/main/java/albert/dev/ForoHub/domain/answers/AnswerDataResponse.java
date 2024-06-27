package albert.dev.ForoHub.domain.answers;

public record AnswerDataResponse(
        Long Id,
        String messageToAnswer,
        String user,
        String topic,
        String creation_date,
        Boolean online
) {
}
