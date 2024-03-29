package ezenWeb.conteroller;

import ezenWeb.model.dto.BoardDto;
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
        return boardService.postBoard(boardDto);
    }
    @GetMapping("/get.do")
    public List<Object> getBoard(){
        return boardService.getBoard();
    }
    @PutMapping("/put.do")
    public boolean putBoard(){
        return boardService.putBoard();
    }
    @DeleteMapping("/delete.do")
    public boolean deleteBoard(){
        return boardService.deleteBoard();
    }


}
