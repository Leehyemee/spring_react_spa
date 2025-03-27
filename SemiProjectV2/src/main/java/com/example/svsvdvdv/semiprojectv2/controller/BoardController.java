package com.example.svsvdvdv.semiprojectv2.controller;

import com.example.svsvdvdv.semiprojectv2.domain.Board;
import com.example.svsvdvdv.semiprojectv2.domain.BoardDTO;
import com.example.svsvdvdv.semiprojectv2.domain.BoardListDTO;
import com.example.svsvdvdv.semiprojectv2.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="http://localhost:5173")
@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity<?> writeok(@RequestBody Board board) {
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("submit된 게시판 데이터 : {}", board);

        try {
            //if (!googleRecaptchaService.verifyRecaptcha(gRecaptchaResponse)) {
            //    throw new IllegalStateException("자동가입방지 코드 오류!!");
            //}
            boardService.newBoard(board);
            response = ResponseEntity.ok().build();
        } catch (IllegalStateException ex) {
            response = ResponseEntity.badRequest().body(ex.getMessage());
        }

        return response;
    }

    // list 엔드포인트 변경
    // http://localhost:8080/api/board/list?cpg=4 아래로 변경(/list -> /list/{cpg} , RequestParam -> PathVariable)
    // http://localhost:8080/api/board/list/4
    @GetMapping("/list/{cpg}")
    public ResponseEntity<?> list(@PathVariable int cpg) {      // java에서 PathVariable === {cpg}
        BoardListDTO boardListDTO = boardService.readBoard(cpg);

        return new ResponseEntity<>(boardListDTO, HttpStatus.OK);
    }

    @GetMapping("/find/{findtype}/{findkey}/{cpg}")
    public ResponseEntity<?> find(@PathVariable int cpg,
          @PathVariable String findtype, @PathVariable String findkey) {
        BoardListDTO boardListDTO = boardService.findBoard(cpg, findtype, findkey);

        return new ResponseEntity<>(boardListDTO, HttpStatus.OK);
    }

    @GetMapping("/test/{cpg}")
    public ResponseEntity<?> test(@PathVariable int cpg) {
        Page<BoardDTO> pageboards = boardService.testReadBoard(cpg);

        return new ResponseEntity<>(pageboards, HttpStatus.OK);
    }


}
