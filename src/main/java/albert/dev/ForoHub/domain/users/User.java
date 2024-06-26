package albert.dev.ForoHub.domain.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "User")
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "Id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String cellphone;
    private String email;
    private String country;
    private String password;
    private Boolean online;

    public User(UserDataRecord userDataRecord) {
        this.name = userDataRecord.name();
        this.cellphone = userDataRecord.cellphone();
        this.email = userDataRecord.email();
        this.country = userDataRecord.country();
        this.password = userDataRecord.password();
    }

    public void updateUserData(UpdateUserDataDTO updateUserDataDTO) {
        if(updateUserDataDTO.name() != null){
            this.name = updateUserDataDTO.name();
        }
        if(updateUserDataDTO.email() != null){
            this.email = updateUserDataDTO.email();
        }
        if(updateUserDataDTO.password() != null){
            this.password = updateUserDataDTO.password();
        }
    }

    public void deleteUserData() {
    this.online = false;
    }
}
