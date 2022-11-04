package miniproject.board.service;

import miniproject.board.domain.Board;
import miniproject.board.repository.SpringDataJpaBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoardService {

    private final SpringDataJpaBoardRepository boardRepository;

    @Autowired
    public BoardService(SpringDataJpaBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

//    public Optional<Board> showBoardById(Long id) {
//        return boardRepository.findById(id);
//    }

    public Board showBoardById(Long id) {
        return boardRepository.findById(id).get();
    }

    public Page<Board> showBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }


}
