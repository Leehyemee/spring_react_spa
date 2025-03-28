import React, {useEffect, useState, useRef} from "react";
import {Link, useParams} from "react-router-dom";
import "../styles/board.css"


const BoardView = () => {
    const [boardData, setBoardData] = useState({});
    const params = useParams();
    const bno = params.bno;

    const fetchURL = `http://localhost:8080/api/board/view/${bno}`;

    // react에서 부수작업(side effect)을 수행하기 위한 hook
    // 부수작업 : 데이터 가져오기, DOM 조작, 로그
    useEffect(() => {
        fetch(fetchURL, {
            headers: { 'Accept' : 'application/json' }
        }).then(res => res.json())
            .then(data => {
                console.log(data);
                setBoardData(data);
            })
            .catch(err => console.log('오류발생!! ', err));
    }, []);

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