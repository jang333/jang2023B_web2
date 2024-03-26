import { useState } from "react"

export default function SignUp(props){

    const [name, setName] = useState('');
    const [gender, setGender] = useState("남자");

    const handleChageName = (e) => {
        setName(e.target.value);
        console.log(e);
    }
    
    const handleChageGender = (e) => {
        setGender(e.target.value);
        console.log(e.target.value);
    }

    const handleSubmit = (e) => {
        alert(`이름 : ${name}, 성별 : ${gender}` );
        e.preventDefault();
    };

    return(<>
        <form onSubmit={handleSubmit}>
            이름 : <input type="text" value={name} onChange={handleChageName} />
            <button type="submit">제출</button>
            성별 : 
            <select value={gender} onChange={handleChageGender}>
                <option value="남자">남자</option>
                <option value="여자">여자</option>
            </select>
        </form>
    </>)
}