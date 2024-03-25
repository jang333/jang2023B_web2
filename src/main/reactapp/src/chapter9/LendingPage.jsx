import { useState } from "react"
import Toolbar from "./Toolbat";

export default function LendingPage(props){
    
    //1. state 상태변수
    const[isLoggedIn, setIsLoggedIn] = useState(false);
    //console.log(isLoggedIn);

    //2. 로그인 클릭함수
    const onClickLogin = () => {
        setIsLoggedIn(true);
    }

    //3. 로그아웃 클릭함수
    const onClickLogout = () =>{
        setIsLoggedIn(false);
    }

    return(<>
        <div>
            <Toolbar
                isLoggedIn = {isLoggedIn}
                onClickLogin = {onClickLogin}
                onClickLogout = {onClickLogout}
            />
            <div>소플과 함께하는 리액트 공부!</div>
        </div>

    </>)
}