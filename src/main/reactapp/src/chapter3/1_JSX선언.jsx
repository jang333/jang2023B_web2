
import React from "react";

const user = {name : '강호동', age : 10} //js 객체(전역)

function formatName(user){  //js 함수
    return user.name + '' + user.age;
}

function JSX선언(props){ //js함수 = 컴퍼넌트 함수

    const name = '유재석'; //js변수

    return (
        <div>
            Hello World<br/>
            저는 {name} 입니다.<br/>
            {formatName(user)}
        </div>
    );
}
export default JSX선언; 