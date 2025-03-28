import React, {useEffect, useState, useRef} from "react";
import {Link, useParams} from "react-router-dom";
import "../styles/board.css"


const BoardView = () => {

    return (
        <main id="content">
            <h2>게시판 본문글</h2>
            <div class="row offset-1 col-10 my-3">
                <table class="table">
                    <thead><tr><td>
                        <button type="button" class="btn btn-light">
                            이전게시물</button>
                        &nbsp;
                        <button type="button" class="btn btn-light">
                            다음게시물</button></td>
                        <td class="text-end">
                            <button type="button" class="btn btn-primary" id="newbdbtn">
                                새글쓰기</button></td>
                    </tr>
                    </thead>
                    <tbody id="boardView">

                    </tbody>
                    <tfoot><tr><td>
                        <button type="button" class="btn btn-warning">
                            수정하기</button>
                        &nbsp;
                        <button type="button" class="btn btn-danger" id="rmvbdbtn">
                            삭제하기</button></td>
                        <td class="text-end"><button type="button" class="btn btn-light" id="lstbdbtn">
                            목록으로</button></td>
                    </tr>
                    </tfoot>
                </table>

                <div class="my-3">
                    <h3><i class="fa fa-commenting"></i> 나도 한마디</h3>
                </div>
            </div>
        </main>
    )
}

export default BoardView;