"use client"
import React, {useRef, useState} from "react";
import "@/app/board/board.css"

const validateBoardForm = (values) => {
    let formErrors = {};

    if (!values.userid) {
        formErrors.userid = '아이디를 입력하세요';
    } else if (values.userid.length < 6) {
        formErrors.userid = '아이디는 6자 이상이어야 합니다';
    }

    if (!values.title) {
        formErrors.title = '글 제목을 입력하세요';
    }
    processBoardok
    if (!values.contents) {
        formErrors.contents = '본문글을 입력하세요';
    }

    return formErrors;
};

const processBoardok = async (formValues) => {
    fetch('http://localhost:8080/api/board/write', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formValues)
    }).then(async response => {
        if (response.ok) {
            alert('글쓰기가 완료되었습니다!!');
            location.href = '/board/list';
        } else if (response.status === 400) {
            alert(await response.text());
        } else {
            alert('게시판 글쓰기에 실패했습니다!! 다시 시도해 주세요!');
        }
    }).catch(error => {
        console.error('error:', error);
        alert('서버와 통신중 오류가 발생했습니다!! 관리자에게 문의하세요!');
    });
};

// BoardList 함수 컴포넌트 정의
const BoardWrite = () => {
    const formBoardRef = useRef(null);
    const [errors, setErrors] = useState({});

    const handleBoardSubmit = (e) => {
        e.preventDefault();

        const formData = new FormData(formBoardRef.current);
        const formValues = Object.fromEntries(formData.entries());

        const formErrors = validateBoardForm(formValues);

        if (Object.keys(formErrors).length === 0) {
            processBoardok(formValues);
        } else {
            setErrors(formErrors);
            console.log('게시판 글쓰기 실패!!');
        }
    }

    return (
        <main id="content">
            <h2>게시판 글쓰기</h2>
            <form name="boardfrm" id="boardfrm" method="post"
                  ref={formBoardRef} onSubmit={handleBoardSubmit} noValidate>
                <div className="form-floating my-2">
                    <input type="text" name="userid" id="userid"
                       className={`form-control ${errors.userid ? 'is-invalid' : ''}`}
                       placeholder="아이디" readOnly value="abc123"/>
                    <label htmlFor="userid" className="form-label">아이디</label>
                    {errors.userid && <div className="invalid-feedback">{errors.userid}</div>}
                </div>

                <div className="form-floating my-2">
                    <input type="text" name="title" id="title"
                       className={`form-control ${errors.title ? 'is-invalid' : ''}`}
                       placeholder="제목" required/>
                    <label htmlFor="title" className="form-label">제목</label>
                    {errors.title && <div className="invalid-feedback">{errors.title}</div>}
                </div>

                <div className="form-floating my-2">
                    <textarea name="contents" id="contents"
                          className={`form-control h-100 ${errors.contents ? 'is-invalid' : ''}`}
                          rows="10" placeholder="본문글" required></textarea>
                    <label htmlFor="contents" className="form-label">본문글</label>
                    {errors.contents && <div className="invalid-feedback">{errors.contents}</div>}
                </div>

                <div className="my-2 d-flex justify-content-center">
                    <div className="g-recaptcha" id="recaptcha" data-sitekey=""></div>
                </div>

                <div className="my-2 d-flex justify-content-between">
                    <button type="submit" className="btn btn-primary">
                        <i className="fa-sharp fa-solid fa-file-signature"></i> 입력완료
                    </button>
                    <button type="reset" className="btn btn-danger">
                        <i className="fa-sharp fa-solid fa-eraser"></i> 다시입력
                    </button>
                </div>
            </form>
        </main>
    )
}

export default BoardWrite;
