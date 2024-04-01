import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Logout from "../member/Logout";
import { LoginInfoContext } from "../Index";

export default function Header(props){
    
    const {loginInfo, setLoginInfo} = useContext(LoginInfoContext);

    //컴포넌트 생성시 axios 실행해서 로그인 회원정보 호출
    useEffect(()=>{
        axios.get('/member/login/info/get.do')
            .then((r)=>{console.log(r);
                setLoginInfo(r.data);
            })
            .catch((e)=>{console.log(e)})
            
    },[])
    

    //2. 로그아웃
    const onLogout = ()=>{
        axios.get('/member/logout/get.do')
            .then((r)=> {console.log(r)
            if(r.data){
                alert('로그인성공');
                window.location.href = '/member/login'
            }
            else{alert('로그아웃 실패')}
            })
            setLoginInfo('');
        }

    return(<>
        <div>
            {loginInfo && <span>{loginInfo.memail}님</span>}
            <ul>
                <li><Link to="/">홈</Link></li>
                <li><Link to="/member/signup">회원가입</Link></li>
                <li><Link to="/member/login">로그인</Link></li>
                <li><button onClick={onLogout} type="button">로그아웃</button></li>
            </ul>
        </div>
    </>)
}