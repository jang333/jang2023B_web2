import axios from "axios"
import { useEffect, useState } from "react"
import MediaCard from "./MediaCard";


export default function BoardTotal(props){

    const [boardList, setBoardList] = useState([]);

    useEffect(()=>{
        axios.get('/board/get.do')
        .then((r)=>{
            setBoardList(r.data);
            //console.log(r.data);
        })
        .catch((e)=>{console.log(e)})
    },[])
        
    return(<>

        <div style={{display : "flex"}}>
            {boardList.map((i)=>{
                console.log(i)
                return (<>
                    <MediaCard board ={i} />
                </>)
                })
            }
        </div>

        
    </>)
}