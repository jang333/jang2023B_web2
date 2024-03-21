//css 호출 : import styles from '경로';
import styles from './Comment.css';

//이미지파일 호출 : import 변수명 from '경로';
import image from './image.jpg';

export default function Comment(props){
    console.log("props :"); console.log(props);
    return(
        
        <div class="wrapper">            
            <div>
                <img class="image" src={image} />
            </div>
            <div class="contentContainer">
                <span class="nameText"> {props.name} </span>
                <span class="commentText"> {props.comment}</span>
            </div>
        </div>
    );
}
