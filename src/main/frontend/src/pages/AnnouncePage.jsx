import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import axios from "axios";


function AnnouncePage(){

    const { id } = useParams();
    const [data,setData] = useState([]);

    useEffect(() => {
        axios.get(`/announce/${id}`)
            .then(response =>
            {
                setData(response.data);
            })
            .catch(error => console.log(error));
    }, [id]);

    return (
        <div style={{marginTop:"200px",textAlign:"center"}}>
            <p>{data?.title}</p>
            <p>{data?.content}</p>
        </div>
    )

}

export default AnnouncePage;