import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// *내가 만든 컴포넌트 import 호출
//import 컴포넌트 from 컴포넌트파일경로;
import JSX선언 from './chapter3/1_JSX선언';

//chapter3 실습
import Book from './chapter3/Book';
import Library from './chapter3/Library';

//chapter4 실습
import Clock from './chapter4/Clock';

//chapter5 실습
import CommentList from './chapter5/CommentList';

//chapter7 예제/실습
import Counter from './chapter7/Counter';
import UseStateList from './chapter7/UseStateList';
import Counter2 from './chapter7/Counter2';

//chapter8 예제/실습
import ConfirmButton from './chapter8/ConfirmButton';

//chapter9 예제
import LendingPage from './chapter9/LendingPage';

//chapter10 예제
import AttendanceBook from './chapter10/AttendanceBook';

//chapter11 예제
import NameForm from './chapter11/NameForm';
//import SignUp from './chapter11/SignUp';

//
//import SignUp from './component/member/SignUp';

//chapter0 axios
import Axios컴포넌트 from './chapter0/Axios컴포넌트';
//chapter0 route
import Route컴포넌트 from './chapter0/Route컴포넌트';

// web2 라우터 컴포넌트
import Index from './component/Index';

//chapter12 실습
import Calculator from './chapter12/Calculator';

//chapter13 실습
import ProfileCard from './chapter13/ProfileCard';

//chapter14 실습
import DarkOrLight from './chapter14/DarkOrLight';
import TextInputWithFoucusButton from './chapter14/TextInputWithFoucusButton';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

    < Index/>

);
//1.setInterval(함수(), 밀리초) : 밀리초마다 해당 함수 실행
// setInterval(() => {
//   root.render(
//     <Clock/>
//   )
// }, 1000);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
