package albert.dev.ForoHub.controller;

import albert.dev.ForoHub.domain.message.*;
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
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping
        public ResponseEntity<MessageDataResponse> submitMessage(@RequestBody @Valid MessageDataRecord messageDataRecord, UriComponentsBuilder uriComponentsBuilder){
            Message message = messageRepository.save(new Message(messageDataRecord));
            MessageDataResponse messageDataResponse = new MessageDataResponse(message.getId(), message.getUser(),message.getTopic(),
                    message.getMessage(), message.getCreation_date(), message.getOnline());
            URI url = uriComponentsBuilder.path("/message/{id}").buildAndExpand(message.getId()).toUri();
            return ResponseEntity.created(url).body(messageDataResponse);
    }
    @GetMapping
        public Page<MessageDataRecordList> messageDataRecordList(@PageableDefault Pageable pagination){
            return messageRepository.findAll(pagination).map(MessageDataRecordList::new);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<MessageDataResponse> updateMessage(@RequestBody @Valid UpdateMessageDTO updateMessageDTO){
        Message message = messageRepository.getReferenceById(updateMessageDTO.Id());
        message.updateData(updateMessageDTO);
        return ResponseEntity.ok(new MessageDataResponse(message.getId(), message.getUser(),message.getTopic(),
                message.getMessage(), message.getCreation_date(), message.getOnline()));
    }

    //Logical Delete
    @DeleteMapping("{Id}")
    @Transactional
    public ResponseEntity<MessageDataResponse> deleteMessage(@PathVariable Long Id){
        Message message = messageRepository.getReferenceById(Id);
        message.logicalDelete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<MessageDataResponse> getMessageById(@PathVariable Long Id){
        Message message = messageRepository.getReferenceById(Id);
        var messageData = new MessageDataResponse(message.getId(), message.getUser(),message.getTopic(),
                message.getMessage(), message.getCreation_date(), message.getOnline());
        return ResponseEntity.ok(messageData);
    }
}
