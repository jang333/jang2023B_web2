import { useContext, useEffect, useRef, useState } from "react";
import { LoginInfoContext } from "../Index";
import styles from "./Chatting.css";
import * as React from 'react';
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import { type } from "@testing-library/user-event/dist/type";


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
            msgList.push(JSON.parse(e.data));
            setMsgList([...msgList]);
        }
                //-4. 클라이언트소캣이 open 발생했을때 콜백함수 정의
        clientSocket.current.onopen = (e)=>{console.log(e); console.log('서버소캣연결성공')}
    }
    
    

        //4. 연결종료
    //clientSocket.close();


    // ======================================
   
    const {loginInfo} = useContext(LoginInfoContext);
    const date = new Date();
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const sendTime = `${hours}시 ${minutes}분`
    console.log(date)

    const onSend = (e)=>{                
        let info = {
            msg : msgInput,                 // 작성된 내용
            fromMname : loginInfo.mname,   // 현재 로그인된 작성자
            date : sendTime,
            type : 'msg'
        }
            //3. 연결된 서버소캣에게 메시지 보내기
                //send() : 데이터 타입 : 텍스트(HTTP 프로토콜)
                    //JSON -> 문자 : JSON.stringify(js객체)
                    //문자 -> JSON : JSON.parse(문자열)
        clientSocket.current.send(JSON.stringify(info)); //입력받은 데이터를 서버소켓에게 보내기
        e.preventDefault();
        //채팅내용입력창 초기화
        setMsgInput('');
    }   

    //- 채팅 내용 입력창
    const [msgInput, setMsgInput] = useState('');
    //- 채팅 창의 내용물들
    const [msgList, setMsgList] = useState([]);
    //- 채팅 내용 입력창에서 엔터키를 했을때 /ctrl + 엔터 했을때
    const activeEnter = (e)=>{
        //console.log(e)
        if(e.keyCode == 13&& e.ctrlKey){
            setMsgInput(msgInput+'\n'); return;
        }
        if(e.keyCode == 13){//엔터 눌럿을때
            onSend(e);
        }
    }
    // - 스크롤 자동으로 최하단으로 내리기
    useEffect (()=>{
        //1.
        let chatcont = document.querySelector('.chatcont');
        console.log(chatcont.scroll);
        console.log(chatcont.scrollTop);    // 현재 스크롤의 상단위치
        console.log(chatcont.scrollHeight); //스크롤 전체 높이길이 (본문이 길어졌기 때문)
        //2.
        chatcont.scrollTop = chatcont.scrollHeight;
    })

    //- 드롭다운 MUI
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
    setAnchorEl(null);
    }; 

    //console.log(Array(43)); //길이만큼의 배열 선언
    //console.log(Array(43).fill(6));

    //- 이모티콘 이미지를 클릭했을때 전송
    const onEmoSend = (emo)=>{
        let info ={
            msg : emo,
            fromMname : loginInfo.mname,
            date : sendTime,
            type : 'emo' //메세지타이(출력시 식별하기 위해)
        }

        clientSocket.current.send(JSON.stringify(info));
        //드롭다운 닫기
        handleClose();        
    }

    //- msg 타입에 따른 HTML 반환 함수
    const typeHTML=(m)=>{
        if(m.type == 'msg'){
            return <div className="content">{m.msg}</div>
        }else if(m.type =='emo'){
            return <img src={'/emo/'+m.msg}/>
        }
    }




    return(<>
        <div>
            <h3>채팅방</h3>
            <div className="chatbox">
                <div className="chatcont">

                    {
                        msgList.map((m)=>{
                            return(<>
                                {
                                    loginInfo.mname == m.fromMname ? 
                                    (<div className="rcont">
                                        <div className="subcont">
                                            <div className="date">{m.date}</div>
                                            {typeHTML(m)}
                                        </div>
                                    </div>) :
                                    <div className="lcont">
                                        <img className="pimg" src="\uploadimg\defalut.jpg" />
                                        <div className="tocont">
                                            <div className="name">{m.fromMname}</div>
                                            <div className="subcont">
                                                {typeHTML(m)}
                                                <div className="date">오전 10 : 45</div>
                                            </div>
                                        </div>
                                    </div>
                                }
                            </>)
                        })
                    }

                </div>
                <div className="chatbottom">
                    <textarea value={msgInput} 
                    onChange={(e)=>{setMsgInput(e.target.value)}}
                    onKeyDown={activeEnter}>
                        
                    </textarea>
                    <button type="button" onClick={onSend}>전송</button>
                </div>
            <div>
            <div>
                <Button
                    id="basic-button"
                    aria-controls={open ? 'basic-menu' : undefined}
                    aria-haspopup="true"
                    aria-expanded={open ? 'true' : undefined}
                    onClick={handleClick}
                >
                    이모티콘
                </Button>
                <Menu
                    id="basic-menu"
                    anchorEl={anchorEl}
                    open={open}
                    onClose={handleClose}
                    MenuListProps={{
                    'aria-labelledby': 'basic-button',
                    }}
                >
                    <div style={{height:200, overflow:'scroll'}}>
                    {
                        Array(43).fill().map((v,i)=>{
                            return (<>

                                <img src={`/emo/emo${i+1}.gif`} onClick={()=>onEmoSend(`emo${i+1}.gif`)} />
                                {(i+1) % 5 == 0 && <br/>}
                            
                            </>)
                        })
                    }
                    </div>
                    
                </Menu>
                </div>

            </div>                
            </div>
            

        </div>
    </>)
}