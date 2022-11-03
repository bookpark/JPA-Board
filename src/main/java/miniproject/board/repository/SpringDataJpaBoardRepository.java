package miniproject.board.repository;

import miniproject.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaBoardRepository extends JpaRepository<Board, Long> {
}
