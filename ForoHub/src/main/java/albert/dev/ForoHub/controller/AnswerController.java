package albert.dev.ForoHub.controller;


import albert.dev.ForoHub.domain.answers.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    private AnswersRepository answersRepository;

    @PostMapping
        public ResponseEntity<AnswerDataResponse> submitAnswer(@RequestBody @Valid AnswerDataRecord answerDataRecord, UriComponentsBuilder uriComponentsBuilder){
        Answers answers = answersRepository.save(new Answers(answerDataRecord));
        AnswerDataResponse answerDataResponse = new AnswerDataResponse(answers.getId(), answers.getUser(),answers.getMessageToAnswer(),answers.getTopic(), answers.getCreation_date(), answers.getOnline());
        URI url = uriComponentsBuilder.path("/answer/{Id}").buildAndExpand().toUri();
        return ResponseEntity.created(url).body(answerDataResponse);
    }

    @GetMapping
        public Page<AnswerDataRecordList> answerDataRecordList(@PageableDefault Pageable pagination){
        return answersRepository.findAll(pagination).map(AnswerDataRecordList::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<AnswerDataResponse> updateAnswer(@RequestBody @Valid UpdateAnswersDTO updateAnswersDTO){
        Answers answers = answersRepository.getReferenceById(updateAnswersDTO.Id());
        answers.updateData(updateAnswersDTO);
        return ResponseEntity.ok(new AnswerDataResponse(answers.getId(), answers.getUser(),
                answers.getMessageToAnswer(),answers.getTopic(), answers.getCreation_date(), answers.getOnline()));
    }

    @DeleteMapping("{Id}")
    @Transactional
    public ResponseEntity<AnswerDataResponse> deleteAnswer(@PathVariable Long Id){
        Answers answers = answersRepository.getReferenceById(Id);
        answers.logicalDelete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/answers/{id}")
    public ResponseEntity<AnswerDataResponse> getAnswerById(@PathVariable Long Id){
        Answers answers = answersRepository.getReferenceById(Id);
        var answersData = new AnswerDataResponse(answers.getId(), answers.getUser(),
                answers.getMessageToAnswer(),answers.getTopic(), answers.getCreation_date(), answers.getOnline());
        return ResponseEntity.ok(answersData);
    }
}
