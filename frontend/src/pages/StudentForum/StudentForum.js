import React, {useState, useEffect} from "react";
import axios from 'axios';


const StudentForum = () => {
    const [posts, setPosts] = useState([]);
    const getPosts = () => {
        return axios.get('http://localhost:8081/api/post/all').then((data) => {
            setPosts(data.data);
        });
    }
    const vote = (id, voteType) => {
        axios.put(`http://localhost:8081/api/post/vote/${id}`, null, {
            params: {
                vote: voteType
            }
        }).then((response) => {
            let tempPosts = posts.map(p => {
                if (p.id === response.data.id) {
                    return response.data;
                }
                return p;
            });
            console.log(tempPosts);
            setPosts(tempPosts);
        });
    }
    useEffect(() => {
        getPosts();
    }, []);

    return (
        <div style={{
            maxHeight: '100vh',
            overflowY: 'auto',
            padding: 32,
            display: "flex",
            flexDirection: "column",
            gap: 24
        }}>
            <div
                style={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "space-between",
                    flexDirection: "row",
                }}>
                <div style={{display: "flex", flexDirection: "column"}}>
                    <span style={{fontSize: 24, fontWeight: "500", color: "#506C68"}}>Student Forum</span>
                    <span style={{fontSize: 16, color: '#2DAA94'}}>SHARE YOU KNOWLEDGE HERE</span>
                </div>
                <div style={{display: "flex", gap: 8}}>
                    <button style={{
                        backgroundColor: '#2DAA94',
                        outline: "none",
                        border: "none",
                        paddingTop: 10,
                        paddingBottom: 10,
                        paddingLeft: 24,
                        paddingRight: 24,
                        borderRadius: 32,
                        textAlign: "center",
                        fontSize: 10,
                        color: '#FFFFFF',
                        cursor: "pointer"
                    }}>+ Add new
                    </button>
                    <button style={{
                        backgroundColor: '#2DAA944F',
                        outline: "none",
                        border: "none",
                        paddingTop: 10,
                        paddingBottom: 10,
                        paddingLeft: 24,
                        paddingRight: 24,
                        borderRadius: 32,
                        textAlign: "center",
                        fontSize: 10,
                        color: '#2DAA94',
                        cursor: "pointer"
                    }}>Filter
                    </button>
                </div>
            </div>
            <div style={{
                autoScroll: true,
                backgroundColor: '#2DAA944F',
                borderRadius: 10,
                padding: 32,
                display: "flex",
                flexDirection: "column",
                gap: 16
            }}>
                {posts.map(a => (
                    <>
                        <div
                            style={{
                                display: "flex",
                                alignItems: "center",
                                justifyContent: "space-between",
                                flexDirection: "row",

                            }}>
                            <div style={{display: "flex", alignItems: "center", gap: 12,}}>
                                <div style={{width: 32, height: 32, borderRadius: 16, backgroundColor: "black"}}/>
                                <div style={{display: "flex", flexDirection: "column"}}>
                                    <span style={{fontWeight: "500", color: "#506C68"}}>{a.username}</span>
                                    <span style={{fontSize: 12, color: '#2DAA94'}}>{a.subject}</span>
                                </div>
                            </div>
                            <div style={{display: "flex", gap: 8}}>
                                <button onClick={() => vote(a.id, "UPVOTE")} style={{
                                    backgroundColor: '#2DAA944F',
                                    outline: "none",
                                    border: "none",
                                    paddingTop: 10,
                                    paddingBottom: 10,
                                    paddingLeft: 24,
                                    paddingRight: 24,
                                    borderRadius: 32,
                                    textAlign: "center",
                                    fontSize: 10,
                                    color: 'white',
                                    cursor: 'pointer',
                                }}>Upvote {a.upVote}
                                </button>
                                <button onClick={() => vote(a.id, "DOWNVOTE")} style={{
                                    backgroundColor: '#2DAA944F',
                                    outline: "none",
                                    border: "none",
                                    paddingTop: 10,
                                    paddingBottom: 10,
                                    paddingLeft: 24,
                                    paddingRight: 24,
                                    borderRadius: 32,
                                    textAlign: "center",
                                    fontSize: 10,
                                    color: 'white',
                                }}>Downvote {a.downVote}
                                </button>
                            </div>
                        </div>
                        <span>{a.content}</span>
                        <button style={{
                            fontSize: 10,
                            color: '#FFF', backgroundColor: '#2DAA94',
                            outline: "none",
                            border: "none",
                            paddingTop: 10,
                            paddingBottom: 10,
                            paddingLeft: 24,
                            paddingRight: 24,
                            borderRadius: 32,
                            textAlign: "center",
                            cursor: "pointer"
                        }}>comments
                        </button>
                    </>
                ))}

            </div>
        </div>
    )
}

export default StudentForum