package com.example.svsvdvdv.semiprojectv2.service;

import com.example.svsvdvdv.semiprojectv2.domain.Board;
import com.example.svsvdvdv.semiprojectv2.domain.BoardDTO;
import com.example.svsvdvdv.semiprojectv2.domain.BoardListDTO;
import com.example.svsvdvdv.semiprojectv2.domain.BoardReplyDTO;
import org.springframework.data.domain.Page;

public interface BoardService {

    Board newBoard(Board board);

    BoardListDTO readBoard(int cpg);

    BoardListDTO findBoard(int cpg, String findtype, String findkey);

    Page<BoardDTO> testReadBoard(int cpg);

    BoardReplyDTO readOneBoardReply(Long bno);

    BoardReplyDTO readOneBoardWithReply(Long bno);
}
