export default function Card(props){
    
    const {title,backgroundColor,children} = props;
    
    return(<>
        <div>
            {title && <h1>{title}</h1>}
            {children}
        </div>
    </>)
}