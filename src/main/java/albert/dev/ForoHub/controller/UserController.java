package albert.dev.ForoHub.controller;

import albert.dev.ForoHub.domain.users.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //UserDataRecord works like the DTO from the user info
    @PostMapping
    public ResponseEntity<UserDataResponse> signInUser(@RequestBody @Valid UserDataRecord userDataRecord, UriComponentsBuilder uriComponentsBuilder){
        User user = userRepository.save(new User(userDataRecord));
        UserDataResponse userDataResponse = new UserDataResponse(user.getId(),
                user.getName(), user.getCellphone(),
                user.getEmail(), user.getCountry(),user.getPassword(), user.getOnline());
        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(userDataResponse);
    }

    @GetMapping
    public ResponseEntity<Page<UserDataRecordList>>  userList(@PageableDefault Pageable pagination){
        return ResponseEntity.ok(userRepository.findByOnlineTrue(pagination).map(UserDataRecordList::new));
    }
    @PutMapping
    @Transactional
    public ResponseEntity<UserDataResponse> updateUserData(@RequestBody @Valid UpdateUserDataDTO updateUserDataDTO){
        User user = userRepository.getReferenceById(updateUserDataDTO.Id());
        user.updateUserData(updateUserDataDTO);
        return ResponseEntity.ok(new UserDataResponse(user.getId(),
                user.getName(), user.getCellphone(),
                user.getEmail(), user.getCountry(),user.getPassword(), user.getOnline()));
    }

    @DeleteMapping("{Id}")
    @Transactional
    public ResponseEntity<UserDataResponse> deleteUser(@PathVariable Long Id){
        User user = userRepository.getReferenceById(Id);
        user.deleteUserData();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("{Id}")
    public ResponseEntity<UserDataResponse> getResponseUserData(@PathVariable Long Id){
        User user = userRepository.getReferenceById(Id);
        var userData = new UserDataResponse(user.getId(),
                user.getName(), user.getCellphone(),
                user.getEmail(), user.getCountry(),user.getPassword(), user.getOnline());
        return ResponseEntity.ok(userData);
    }
}
