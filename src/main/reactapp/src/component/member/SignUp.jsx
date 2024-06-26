import axios from "axios"; //axios 라이브러리 호출
import { useState } from "react"

export default function SignUp(props){

    //1. state 상태 변수
    const [memail, setMemail] = useState('');
    const [mpassword, setMpassword] = useState('');
    const [mname, setMname] = useState('');

    //2. memail 수정함수
    const onChangeEmail = (e)=>{
        setMemail(e.target.value);
    }

    //3. 전송함수
    const onSignUp = (e)=>{
        console.log(memail);
        console.log(mpassword);
        console.log(mname);
        /*
            axios.HTTP메소드명("url")
            .then(응답매개변수 => {응답로직})
        */
       let info = {memail : memail, mpassword : mpassword, mname : mname}
        axios.post("/member/signup/post.do", info) //contentType : JSON //오류 4xx
            .then(response => {console.log(response) //성공 200
                if(response.data ==1){
                    alert('회원가입 성공');
                    window.location.href="/member/login";
                }else if(response.data ==2){
                    alert('아이디 중복')
                }
                else{
                    alert('회원가입 실패');
                }
            })
            .catch(error => {console.log(error);}) //오류 5xx
            
    }

    return(<>
        <form>
            이메일 : 
            <input 
                value={memail} 
                type="text"
                onChange={onChangeEmail} /> <br/>
            패스워드 :
            <input
                type="password"
                value={mpassword}
                onChange={(e)=>setMpassword(e.target.value)}/> <br/>
            닉네임 : 
            <input 
                type="text"
                value={mname}
                onChange={(e)=>setMname(e.target.value)}/><br/>
            <button
                type="button"
                onClick={onSignUp}> 회원가입               
            </button>
        </form>

    </>)
}