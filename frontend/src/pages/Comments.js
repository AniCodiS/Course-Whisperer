import React, {useState, useEffect} from "react";
import axios from 'axios';


const Comments = ({postId}) => {
    const username = localStorage.getItem("username");
    const [comments, setComments] = useState([]);
    const [content, setContent] = useState([]);
    const [showComments, setShowComments] = useState(false);
    const getComments = async () => {
        try {
            const response = await axios.get(`http://localhost:8081/api/comment/${postId}/all`);
            setComments(response.data);
        } catch (error) {
            alert(error.response.messageDiscription)
        }
    };

    const handleToggleComments = () => {
        setShowComments(prevShowComments => !prevShowComments);
    };

    useEffect(() => {
        if (showComments) {
            getComments();
        }
    }, [showComments]);

    return (
        <>
            <button onClick={handleToggleComments} style={{
                fontSize: 10,
                color: '#FFF',
                backgroundColor: '#2DAA94',
                outline: "none",
                border: "none",
                paddingTop: 10,
                paddingBottom: 10,
                paddingLeft: 24,
                paddingRight: 24,
                borderRadius: 32,
                textAlign: "center",
                cursor: "pointer"
            }}> Comments
            </button>
            {showComments &&
                <div style={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "space-between",
                    flexDirection: "column",
                    backgroundColor: '#2DAA944F',
                    outline: "none",
                    border: "none",
                    paddingTop: 10,
                    paddingBottom: 10,
                    paddingLeft: 24,
                    paddingRight: 24,
                    borderRadius: 10,
                    fontSize: 10,
                    color: '#2DAA94',
                    textAlign: "center"
                }}>
                    {
                        comments.map(
                            a => (
                                <div style={{
                                    padding: 15,
                                    marginBottom: 16,
                                    display: "flex",
                                    flexDirection: "column",
                                    backgroundColor: '#2DAA944F',
                                    borderRadius: 10
                                }}>
                                    <div
                                        style={{
                                            display: "flex",
                                            alignItems: "center",
                                            justifyContent: "space-between",
                                            flexDirection: "row",
                                        }}> {a.content}
                                    </div>
                                </div>
                            )
                        )
                    }
                </div>
            }
        </>
    )
}

export default Comments;