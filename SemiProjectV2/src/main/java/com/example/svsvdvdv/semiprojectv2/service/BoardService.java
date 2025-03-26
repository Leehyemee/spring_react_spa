package com.example.svsvdvdv.semiprojectv2.service;

import com.example.svsvdvdv.semiprojectv2.domain.Board;
import com.example.svsvdvdv.semiprojectv2.domain.BoardListDTO;

public interface BoardService {

    Board newBoard(Board board);

    BoardListDTO readBoard(int cpg);
}
