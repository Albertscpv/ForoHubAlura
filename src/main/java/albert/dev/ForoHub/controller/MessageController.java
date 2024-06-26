package albert.dev.ForoHub.controller;

import albert.dev.ForoHub.domain.message.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @PostMapping
        public void submitMessage(@RequestBody @Valid MessageDataRecord messageDataRecord){
            messageRepository.save(new Message(messageDataRecord));
    }
    @GetMapping
        public Page<MessageDataRecordList> messageDataRecordList(@PageableDefault Pageable pagination){
            return messageRepository.findAll(pagination).map(MessageDataRecordList::new);
    }
    @PutMapping
    @Transactional
    public void updateMessage(@RequestBody @Valid UpdateMessageDTO updateMessageDTO){
        Message message = messageRepository.getReferenceById(updateMessageDTO.Id());
        message.updateData(updateMessageDTO);
    }

    //Logical Delete
    @DeleteMapping("{Id}")
    @Transactional
    public void deleteMessage(@PathVariable Long Id){
        Message message = messageRepository.getReferenceById(Id);
        message.logicalDelete();
    }
}
