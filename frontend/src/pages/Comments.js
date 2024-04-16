import React, {useState, useEffect} from "react";
import axios from 'axios';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faTrashAlt} from '@fortawesome/free-solid-svg-icons';


const Comments = ({postId}) => {
    const username = localStorage.getItem("username");
    const [comments, setComments] = useState([]);
    const [content, setContent] = useState('');
    const [showComments, setShowComments] = useState(false);

    const getComments = async () => {
        try {
            const response = await axios.get(`http://localhost:8081/api/comment/${postId}/all`);
            setComments(response.data);
        } catch (error) {
            alert(error.response.messageDiscription)
        }
    };

    const addComment = async () => {
        try {
            const response = await axios.put(`http://localhost:8081/api/post/${postId}`, null, {
                params: {
                    username,
                    comment: content
                }
            });
            console.log(content)
            if (response.status === 200) {
                setComments([...comments, response.data]);
                setContent('');
            }
        } catch (error) {
            alert(error.response.messageDiscription)
        }
    };

    const deleteComment = async (id) => {
        console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", id);
        try {
            await axios.delete(`http://localhost:8081/api/comment/remove/${id}`, {
                params: {
                    username,
                }
            });
            setComments(comments.filter((comment) => comment.id !== id));
        } catch (error) {
            alert(error.response.messageDiscription);
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
                    marginTop: 10,
                    paddingTop: 10,
                    paddingBottom: 10,
                    paddingLeft: 24,
                    paddingRight: 24,
                    borderRadius: 10,
                    fontSize: 10,
                    color: '#2DAA94',
                    textAlign: "center",
                    minHeight: 150,
                    maxHeight: 200,
                    overflowY: 'auto',
                    overflowX: 'hidden'
                }}>
                    {comments.map((comment) => (
                        <div style={{display: 'flex', alignItems: 'center', width: '100%'}}>
                            <div
                                key={comment.id}
                                style={{
                                    backgroundColor: 'white',
                                    border: '1px solid #2DAA94',
                                    borderRadius: '5px',
                                    padding: '8px',
                                    marginBottom: '8px',
                                    textAlign: 'left',
                                    color: 'black',
                                    wordWrap: "break-word",
                                    width: '90%',
                                }}
                            >
                                {comment.content}
                            </div>
                            {username === comment.username && (
                                <button onClick={() => deleteComment(comment.id)}
                                        style={{
                                            ...trashIconStyle,
                                            alignItems: 'center',
                                            marginLeft: '8px',
                                            marginBottom: '8px',
                                            width: '10%',
                                            display: 'flex',
                                            justifyContent: 'center',
                                            border: 'none'
                                        }}>
                                    <FontAwesomeIcon icon={faTrashAlt} style={{fontSize: '1.2em'}}/>
                                </button>
                            )}
                        </div>

                    ))}
                    <div style={{display: 'flex', alignItems: 'center'}}>
                        <input
                            type="text"
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                            placeholder="Add a comment..."
                            style={{marginRight: '8px'}} // Add some spacing between input and button
                        />
                        <button style={{cursor: 'pointer'}} onClick={addComment}>Add Comment</button>
                    </div>
                </div>
            }
        </>
    )
}

const trashIconStyle = {
    borderRadius: '20%',
    backgroundColor: '#2DAA94',
    color: '#FFF',
    padding: '8px',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    border: 'none',
    cursor: 'pointer'
};

export default Comments;
