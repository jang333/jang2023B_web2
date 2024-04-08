import { useRef, useState } from "react";

export default function Chatting(props){

    //1. 해당 컴포넌트가 렌더링 될때 소켓은 재렌더링 방지 useRef
        //useRef(초기값) : {current:값}
        //- 컴포넌트가 렌더링시 참조값 고정
    let clientSocket = useRef(null);
    //2.
    if(!clientSocket.current){
            // ====== (클라이언트) 웹소켓 구현 ========
            //1. new WebSocket(서버소켓url); //비동기
        clientSocket.current = new WebSocket('ws://192.168.17.128:80/chat');
        //확인
        console.log(clientSocket.current);
        //onclose onerror onmessage onopen : WebSocket 객체내 포함된 메소드들
            //2. 각 메소드 정의
                //-1. 클라이언트소캣이 close 되었을때 콜백함수 정의
        clientSocket.current.onclose = (e)=>{console.log(e);}
                //-2. 클라이언트소캣에 error 발생했을때 콜백함수 정의
        clientSocket.current.onerror = (e)=>{console.log(e);}
                //-3. 클라이언트소캣이 message 받았을때 콜백함수 정의
        clientSocket.current.onmessage = (e) => {console.log(e);
            console.log(e.data)
            msgList.push(e.data); 
            setMsgList([...msgList]);
            console.log(msgList);
        }
                //-4. 클라이언트소캣이 open 발생했을때 콜백함수 정의
        clientSocket.current.onopen = (e)=>{console.log(e); console.log('서버소캣연결성공')}
    }
    
    

        //4. 연결종료
    //clientSocket.close();


    // ======================================
   
    const onSend = ()=>{        
            //3. 연결된 서버소캣에게 메시지 보내기
        clientSocket.current.send(msgInput); //입력받은 데이터를 서버소켓에게 보내기
    }   

    //- 채팅 내용 입력창
    const [msgInput, setMsgInput] = useState('');
    //- 채팅 창의 내용물들
    const [msgList, setMsgList] = useState([]);

    return(<>
        <div>
            <h3>채팅방</h3>
            {<div>
                {
                    msgList.map((msg)=>{
                        return <div>{msg}</div>
                    })
                }
            </div>}
            <textarea value={msgInput} onChange={(e)=>{setMsgInput(e.target.value)}}></textarea>
            <button type="button" onClick={onSend}>전송</button>
        </div>
    </>)
}