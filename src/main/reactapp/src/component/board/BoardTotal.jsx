import axios from "axios"
import { useEffect, useState } from "react"

export default function BoardTotal(props){

    const [boardList, setBoardList] = useState([]);


        useEffect(()=>{
            axios.get('/board/get.do')
            .then((r)=>{
                setBoardList(r.data);
                console.log(r.data);
            })
            .catch((e)=>{console.log(e)})
        },[])        

    

    

    return(<>
        <div>
            {boardList.map((i)=>{
                console.log(i)
                return (<>
                    <li>{i.bcontent}</li>
                    <li>{i.memail}</li>
                </>)
                })
            }

        </div>
        전체글보기

        
    </>)
}