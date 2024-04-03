package ezenWeb.conteroller;

import ezenWeb.model.dto.BoardDto;
import ezenWeb.model.dto.PageDto;
import ezenWeb.model.entity.BoardEntity;
import ezenWeb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //@ResponseBody + @Controller : 데이터를 주고 받은 REST 역할'
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @PostMapping("/post.do")
    public boolean postBoard(BoardDto boardDto){
        System.out.println("boardDto = " + boardDto);
        return boardService.postBoard(boardDto);
    }
    @GetMapping("/get.do")
    public PageDto getBoard(int page, int view){
        return boardService.getBoard(page, view);
    }
    @PutMapping("/put.do")
    public boolean putBoard(){
        return boardService.putBoard();
    }
    @DeleteMapping("/delete.do")
    public boolean deleteBoard(int bno, String memail){
        System.out.println("BoardController.deleteBoard");
        System.out.println("bno = " + bno);
        return boardService.deleteBoard(bno,memail);
    }


}
