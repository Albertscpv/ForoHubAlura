package albert.dev.ForoHub.domain.answers;

public record AnswerDataRecord(
        String messageToAnswer,
        String user,
        String topic,
        String creation_date,
        String answer
) {
}
