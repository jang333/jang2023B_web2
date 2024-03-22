import { useState } from "react";

export default function UseStateList(props){
    //2.point 상태관리변수
        //1. input 입력된 값을 저장하는 상태관리변수
    let [pointInput, setPointInput] = useState('');
        //2. input 입력된 값들을 저장하는 리스트 상태관리변수
    let [pointList, setPointList] = useState([]);

    //1. 등록버튼 클릭시
    function 등록(){
        console.log('등록');
        /*=========== 고전 방식 ===========
        //1. 
        let value = document.querySelector('.pointInput').value;
        //2.
        console.log(value);
        */
        //========== 리액트 방식 ==========
        pointList.push(pointInput);
        setPointList([...pointList]);
   }

   function 입력변경(e){
        setPointInput(e.target.value);

   }

    return(<>
        <div>
            <div>
                <input value={pointInput} class="pointInput"  type="text" onChange={입력변경}/>
                <button type="button" onClick={등록}>등록</button>
            </div>
            <div>
                <div>
                    {
                        pointList.map((point)=>{
                            return(<div>{point}</div>)
                        })
                    }
                </div>
            </div>
        </div>

    </>)    

    /*
        함수적용 2가지 방법
        1. onChange = {입력변경} //함수를 밖에 선언
        2. onChange = {(e)=>{e.target.value}} //안에 넣기
    */
}