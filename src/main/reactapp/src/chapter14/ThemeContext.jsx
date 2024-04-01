import React from "react";

//1. ThemeContext라는 이름으로 컨텍트를 만든다.
const ThemeContext = React.createContext();

//2. 개발자도구에 컨텍트의 이름을 학인하기 위해.
ThemeContext.displayName = "ThemeContext";

export default ThemeContext;

