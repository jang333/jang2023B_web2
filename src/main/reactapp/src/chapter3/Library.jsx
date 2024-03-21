import Book from "./Book";

export default function Library( props ){ 
    return ( 
        <div>
            <Book name="강호동" numOfPage={35}/>
            <Book name="유재석" numOfPage={45}/>
            <Book name="신동엽" numOfPage={50}/>
        </div>
     );
}