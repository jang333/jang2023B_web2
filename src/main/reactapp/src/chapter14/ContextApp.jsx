//1. 컴포넌트1 : 조부모
export default function ContextApp(props){
    return (<>
        <Toolbar theme="dark"/>
    </>)
}
//2. 컴포넌트2 : 부모
function Toolbar(props){
    console.log(props); //props = {theme = "dark"}
    return(<>
        <ThemedButtom theme = {props.theme}/>
    </>)
}
//3. 컴포넌트3 : 자식
function ThemedButtom(props){
    console.log(props); //props = {theme = "dark"}
    return(<>
        <Button theme = {props.theme}/>;
    </>)
}