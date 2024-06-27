package albert.dev.ForoHub.domain.answers;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Answer")
@Table(name = "answer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "Id")
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String messageToAnswer;
    private String user;
    private String topic;
    private String creation_date;
    private String answer;
    private Boolean online;


    public Answers(AnswerDataRecord answerDataRecord){
        this.messageToAnswer = answerDataRecord.messageToAnswer();
        this.user = answerDataRecord.user();
        this.topic = answerDataRecord.topic();
        this.creation_date = answerDataRecord.creation_date();
        this.answer = answerDataRecord.answer();

    }

    public void updateData(UpdateAnswersDTO updateAnswersDTO) {
    if(updateAnswersDTO.messageToAnswer() != null){
        this.messageToAnswer = updateAnswersDTO.messageToAnswer();
    }
    if (updateAnswersDTO.answer() != null){
        this.answer = updateAnswersDTO.answer();
    }
    if(updateAnswersDTO.creation_date() != null){
        this.creation_date = updateAnswersDTO.creation_date();
    }
    }

    public void logicalDelete(){
        this.online = false;
    }

}
