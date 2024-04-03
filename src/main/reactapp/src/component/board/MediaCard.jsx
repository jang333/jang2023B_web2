import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import axios from 'axios';




export default function MediaCard(props) {
  console.log(props);
  
  

  function ondelete(){

    const info = {bno : props.board.bno, memail : props.board.memail}
    console.log(info);

      axios.delete('/board/delete.do',{params:info})
      .then((r)=>{
        console.log(r);
        if(r.data){
          alert('삭제 성공')
          window.location.href = '/board'
        }else{alert('삭제 실패')}

      })
      .catch((e)=>{console.log(e)})  
  }
  
  return (
    <Card sx={{ maxWidth: 400 }} style={{margin: 10}}>

      <CardMedia
        sx={{ height: 200 }}
        image={"/uploadimg/"+ props.board.bimgList[0]}
        title="green iguana"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {props.board.memail}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {props.board.bcontent}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small" onClick={ondelete} bno={props.board.bno}>Delete</Button>
        <Button size="small">Learn More</Button>
      </CardActions>
    </Card>
  );
}

