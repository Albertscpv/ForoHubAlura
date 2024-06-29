package albert.dev.ForoHub.domain.answers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersRepository extends JpaRepository<Answers, Long> {
    Page<Answers> findByOnlineTrue(Pageable pagination);

}
