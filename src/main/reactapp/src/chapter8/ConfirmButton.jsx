import { useState } from "react";

export default function ConfirmButton(props){
    
    //1. 상태 관리 변수 //import{useState} from "react";
    const[isConfirmed, setIsConfirmed] = useState(false);
        //useState : 리액트 훅 중 사용빈도가 높은 함수
            // 사용/호출 : useState(초기값);
            // 반환 : 배열
                //[0] : 값이 저장된 변수
                //[1] : 값을 수정할 수 있는 set함수 [*(주소값)변경시 해당 컴포넌트 렌더링]
                    //setIsConfirmed((매개변수)=>{})
                        //*매개변수로 기존값


    //2. 함수
    function handleConfirm(){        
        setIsConfirmed((prevIsConfirmde)=> !prevIsConfirmde)
        //console.log(prevIsConfirmde);
    }
    
    return (<>
        <button onClick={handleConfirm} disabled={isConfirmed}>
        {isConfirmed?'확인됨':'확인하기'}
        </button>
    </>)
}