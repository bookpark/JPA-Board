package miniproject.board.controller;

import miniproject.board.domain.Board;
import miniproject.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    private BoardService service;

    @Autowired
    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/board-list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.DESC)
                            Pageable pageable) {
        Page<Board> boards = service.showBoardList(pageable);

        int currentPage = boards.getPageable().getPageNumber() + 1;
        int startPage = 1;
        int endPage = boards.getTotalPages();
        model.addAttribute("boards", boards);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board-list";
    }

    @GetMapping("/board-detail")
    public String board() {
        return "board-detail";
    }

    @GetMapping("/board-write")
    public String boardWrite() {
        return "board-write";
    }

    @PostMapping("/board-write")
    public String boardWritePost(Board board) {
        service.saveBoard(board);
        return "redirect:/board-list";
    }

    @GetMapping("/board/{id}")
    public String showBoard(@PathVariable("id") Long id, Model model) {
        Board board = service.showBoardById(id);
        model.addAttribute("board", board);
        return "board-detail";
    }

    @PostMapping("/board-update")
    public String updateBoard(Long id, BoardForm boardForm) {
        Board board = service.showBoardById(id);
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        service.saveBoard(board);
        return "redirect:/board-list";
    }

    @PostMapping("/board-delete")
    public String deleteBoard(Long id) {
        Board board = service.showBoardById(id);
        service.deleteBoardById(id);
        return "redirect:/board-list";
    }

}
