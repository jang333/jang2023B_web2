import axios from "axios"
import { useEffect, useState } from "react"
import MediaCard from "./MediaCard";
import Pagination from '@mui/material/Pagination';


export default function BoardTotal(props){

    const [pageDto, setPageDto] = useState({
        page : 1, count : 0, data:[]
    });
    
    useEffect(()=>{
        const info = {page : pageDto.page, view : 4}
        axios.get('/board/get.do',{params:info})
        .then((r)=>{
            setPageDto(r.data);
            console.log(r.data);
        })
        .catch((e)=>{console.log(e)})
    },[pageDto.page])

    const handleChange = (e, value) => {
        pageDto.page = value;
        setPageDto({...pageDto});
      };

   
        
    return(<>

        <div style={{display : "flex"}}>
            {pageDto.data.map((board)=>{
                console.log(board)
                return (<>
                    <MediaCard board ={board} />
                </>)
                })
            }
        </div>
        <ul>
        <Pagination count={pageDto.count} page={pageDto.page} onChange={handleChange} />
        </ul>

        
    </>)
}