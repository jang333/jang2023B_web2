import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import Home from "./layout/Home";
import SignUp from "./member/SignUp";
import Login from "./member/Login";

export default function Index(props){
    return(<>
        <BrowserRouter>
            <div id="wrap">
                <Header/>

                <Routes>
                    <Route path="/" element={<Home/>}></Route>
                    <Route path="/member/signup" element={<SignUp/>}></Route>
                    <Route path="/member/login" element={<Login/>}></Route>
                </Routes>

                <Footer/>
            </div>            
        </BrowserRouter>    
    </>)
}