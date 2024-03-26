import { useState } from "react";

export default function NameForm(props){
    
    //1. 함수
    function 제출1(){
        let nameInput = document.querySelector('#nameInput').value;
        console.log(nameInput);
    }
    
    //2. 함수2
        //1. state 변수
    const[value, setValue] = useState('');
        //2. state 변경함수 : e(event : 해당 이벤트(change)를 발생한 결과 정보 객체)
    const handleChange = (e)=>{
        setValue(e.target.value);
        e.preventDefault(); //브라우저들의 이벤트들을 제거
        console.log(e);
        console.log(e.target);
    }
   

    //3.textarea
    const [value2, setValue2] = useState('')
    const handleChange2 = (e)=>{
        setValue2(e.target.value);
        e.preventDefault();
        //console.log(e); 
    }

    //4.select
    const [value3, setValue3] = useState('grape');

    const handleChange3 = (e)=>{
        setValue3(e.target.value);
    }

    //*. 제출함수
    const handleSumit = (e)=>{
        console.log(value);
        console.log(value2);
        console.log(value3);
    }

    return(<>
        <form>
            이름 : <input id="nameInput"/>
            <button type="button" onClick={제출1}>제출1</button>
        </form>
        <form onSubmit={handleSumit}>
            이름 : 
            <input 
                type="text" 
                value={value} 
                onChange={handleChange}/> <br/>
            요청사항 : 
            <textarea 
                value={value2} 
                onChange={handleChange2}>                
            </textarea><br/>
            과일을 선택하세요:
            <select value={value3} onChange={handleChange3}>
                <option value='apple'>사과</option>
                <option value='banana'>바나나</option>
                <option value='grape'>포도</option>
                <option value='watermelon'>수박</option>
            </select>
            <button type="button" onClick={handleSumit}>제출2</button>
        </form>
    </>)
}