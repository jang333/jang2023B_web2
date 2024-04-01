import axios from "axios";


export default function Write(props){

    
    
    const onWrite = ()=>{

        const writeForm = document.querySelector("#writeForm");
        const writeFormData = new FormData(writeForm);
        console.log(writeFormData);
        
        axios.post('/board/post.do', writeFormData)
            .then((r)=>{
                console.log(r);
                console.log(writeForm);
                if(r.data){
                    alert('등록 완료')
                    window.location.href = '/board'
                }else{alert('등록 실패')}
            })

    }



    return(<>
        <form id="writeForm">
            <textarea name="bcontent"/>
            <button onClick={onWrite} type="button">등록하기</button>
        </form>
        
    </>)
}