package albert.dev.ForoHub.domain.users;

public record UserDataRecordList(
        Long Id,
        String name,
        String email) {

    public UserDataRecordList(User user){
        this(user.getId(), user.getName(), user.getEmail());
    }
}
