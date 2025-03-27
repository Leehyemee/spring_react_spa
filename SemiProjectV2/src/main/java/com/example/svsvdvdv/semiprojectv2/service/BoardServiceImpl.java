package com.example.svsvdvdv.semiprojectv2.service;

import com.example.svsvdvdv.semiprojectv2.domain.Board;
import com.example.svsvdvdv.semiprojectv2.domain.BoardDTO;
import com.example.svsvdvdv.semiprojectv2.domain.BoardListDTO;
import com.example.svsvdvdv.semiprojectv2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    @Value("${board.pagesize}") private int pageSize;

    @Override
    public Board newBoard(Board board) {

        return boardRepository.save(board);
    }

    @Override
    public BoardListDTO readBoard(int cpg) {
        Pageable pageable = PageRequest.of(cpg, pageSize, Sort.Direction.DESC, "bno");

        Page<BoardDTO> pageboards = boardRepository.findBy(pageable);
        List<BoardDTO> boards = pageboards.getContent();
        int totalItems = (int) pageboards.getTotalElements();

        return new BoardListDTO(cpg, totalItems, pageSize, boards);
    }

    @Override
    public BoardListDTO findBoard(int cpg, String findtype, String findkey) {
        Pageable pageable = PageRequest.of(cpg, pageSize, Sort.Direction.DESC, "bno");
        Page<BoardDTO> pageboards = switch (findtype) {
            case "title" -> boardRepository.findByTitleContains(pageable, findkey);
            case "contents" -> boardRepository.findByContentsContains(pageable, findkey);
            case "userid" -> boardRepository.findByUseridContains(pageable, findkey);
            case "titconts" -> boardRepository.findByTitleContainsOrContentsContains(pageable, findkey, findkey);
            default -> null;
        };

        List<BoardDTO> boards = pageboards.getContent();
        int totalItems = (int) pageboards.getTotalElements();

        return new BoardListDTO(cpg, totalItems, pageSize, boards);
    }

    @Override
    public Page<BoardDTO> testReadBoard(int cpg) {
        Pageable pageable = PageRequest.of(cpg, pageSize, Sort.Direction.DESC, "bno");

        Page<BoardDTO> pageboards = boardRepository.findBy(pageable);

        return pageboards;
    }

}
