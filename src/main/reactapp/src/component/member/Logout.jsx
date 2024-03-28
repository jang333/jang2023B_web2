import axios from "axios"
import { useEffect } from "react"

export default function Logout(props){

    useEffect(() =>{
        axios.get('/member/logout/get.do')
        .then((r)=>{
            console.log(r);
            if(r.data){
                alert('로그아웃 성공')
                window.location.href = "/"
            }else{
                alert('로그아웃 실패')
                window.location.href = "/"
            }
        })
        .catch((e)=> {console.log(e)})
    },[]) 

}