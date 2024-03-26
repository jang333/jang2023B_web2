export default function AttendanceBook(props){
    
    //1. 샘플데이터
    const students = [
        {id : 1, name : "Inje"},
        {id : 2, name : "Steve"},
        {id : 3, name : "Bill"},
        {id : 4, name : "Jeff"}
    ]

    return(<>
        <ul>
            {
                //------ JSX 시작

                students.map((students)=>{
                    return (
                    <li 
                        key={students.id}
                        id={students.id}
                        className={students.id}
                    >
                        {students.name}
                    </li>
                )})

                //------ JSX 끝
            }
        </ul>
    </>)

}