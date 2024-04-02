import axios from "axios";
import { useRef } from "react";


export default function Write(props){

    //1. 재렌더링 고정 참조 변수
    const writeFormRef = useRef(); // {current : undifined}
    console.log(writeFormRef);
    
    const onWrite = ()=>{
        console.log(writeFormRef);  console.log(writeFormRef.current);        
        //2.
        axios.post('/board/post.do', writeFormRef.current) 
            .then((r)=>{
                console.log(r);
                if(r.data){
                    alert('등록 완료')
                    window.location.href = '/board'
                }else{alert('등록 실패')}
            })

    }


    return(<>
        <h3>글쓰기</h3>
        <form ref={writeFormRef}>
            <textarea name="bcontent"/>
            <input type="file" multiple accept="image/*" name="uploadList"/>
            <button onClick={onWrite} type="button">등록하기</button>
        </form>
        
    </>)
}