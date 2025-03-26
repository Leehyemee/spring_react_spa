package com.example.svsvdvdv.semiprojectv2.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class BoardListDTO {
    private int cpg;
    private int stblk;
    private int cntpg;
    private int edblk;
    private List<?> bdlist;

    public BoardListDTO(int cpg, int totalItems, int pageSize, List<?> bdlist) {
        this.cpg = cpg;
        this.cntpg = (int) Math.ceil((double) totalItems / pageSize);
        this.bdlist = bdlist;
        // 페이지네이션 범위 계산
        this.stblk = ((cpg -1) / 10) * 10 + 1;
        this.edblk = Math.min(stblk + 10 - 1, cntpg);

        log.info("{} {} {} {}", totalItems, cntpg, pageSize, edblk);
    }
}
