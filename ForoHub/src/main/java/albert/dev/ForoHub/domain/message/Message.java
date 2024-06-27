package albert.dev.ForoHub.domain.message;
import jakarta.persistence.*;
import lombok.*;



@Entity(name = "Message")
@Table(name = "message")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "Id")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String user;
    private String topic;
    private String message;
    private String creation_date;
    private Boolean online;

    public Message(MessageDataRecord messageDataRecord) {
        this.user = messageDataRecord.user();
        this.message = messageDataRecord.message();
        this.topic = messageDataRecord.topic();
        this.creation_date = messageDataRecord.creation_date();
    }

    public void updateData(UpdateMessageDTO updateMessageDTO) {
        if(updateMessageDTO.message() != null){
            this.message = updateMessageDTO.message();
        }
        if(updateMessageDTO.topic() != null){
            this.topic = updateMessageDTO.topic();
        }
        if(updateMessageDTO.creation_date() != null){
            this.creation_date = updateMessageDTO.creation_date();
        }
    }
    public void logicalDelete(){
        this.online = false;
    }
}
