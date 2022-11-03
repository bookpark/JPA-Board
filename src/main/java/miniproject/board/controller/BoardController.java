package miniproject.board.controller;

import miniproject.board.domain.Board;
import miniproject.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    private BoardService service;

    @Autowired
    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping("/board-list")
    public String home() {
        return ("board-list");
    }

    @GetMapping("/board-detail")
    public String board() {
        return ("board-detail");
    }

    @GetMapping("/board-write")
    public String boardWrite() {
        return ("board-write");
    }

    @PostMapping("/board-write")
    public String boardWritePost(Board board, String title, String content) {
        service.saveBoard(board);
        return ("redirect:/board-list");
    }

}
