package albert.dev.ForoHub.domain.message;

public record MessageDataRecordList(
        Long id,
        String user,
        String message){
    public MessageDataRecordList(Message message){
        this(message.getId(), message.getUser(), message.getMessage());
    }

}
