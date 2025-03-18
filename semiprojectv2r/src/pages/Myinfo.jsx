import React from "react";
import "../styles/member.css";

// Join 함수 컴포넌트 정의
const Join = () => {
    return (
        <main id="content">
            <h2>회원정보</h2>
            <div id="myinfo">
                <table className="table table-bordered mb-0">
                    <tbody>
                    <colgroup>
                        <col style={{width: "30%"}} />
                        <col/>
                    </colgroup>
                    <tr>
                        <td>아이디</td>
                        <td><span>abc123</span></td>
                    </tr>

                    <tr>
                        <td>이름</td>
                        <td><span>abc123</span></td>
                    </tr>

                    <tr>
                        <td>이메일</td>
                        <td><span>abc123@abc123.com</span></td>
                    </tr>

                    <tr>
                        <td>가입일</td>
                        <td><span>2025-03-18 12:35:35</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </main>
    )
}

export default Join;