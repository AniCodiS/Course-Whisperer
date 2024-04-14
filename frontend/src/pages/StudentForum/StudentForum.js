import React, {useState, useEffect} from "react";
import axios from 'axios';


const StudentForum = () => {
    const username = localStorage.getItem("username");
    const [posts, setPosts] = useState([]);
    const [showAddPost, setShowAddPost] = useState(false);
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

    const postTypes = [
        {name: '', value: ''},
        {name: 'Post', value: 'POST'},
        {name: 'Lecture Link', value: 'LECTURELINK'},
        {name: 'Text Book Link', value: 'TEXTBOOKLINK'},
        {name: 'Other', value: 'OTHER'}
    ];

    const [content, setContent] = useState('');
    const [postType, setPostType] = useState('POST');
    const [subject, setSubject] = useState('');

    const handleSubmit = async () => {
        await axios.post('http://localhost:8081/api/post/create', {
            username,
            subject,
            type: postType,
            content,
        },).then((response) => {
            if (response.status === 200) {
                setPosts([...posts, response.data]);
                handleAddPost();
            }
        });
    }

    const handleAddPost = () => {
        if (showAddPost) {
            setContent('');
            setPostType('POST');
            setSubject('')
        }
        setShowAddPost(prev => !prev);
    }

    const [filterObj, setFilterObj] = useState(
        {
            subject: '',
            type: '',
        }
    );

    const handleFilterButton = () => {
        axios.get('http://localhost:8081/api/post/search', {
            params: {
                subject: filterObj.subject,
                type: filterObj.type,
                username
            }
        }).then((response) => {
            setPosts(response.data);
        });
    }


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
                    <span style={{fontSize: 16, color: '#2DAA94'}}>Share Your Knowledge Here</span>
                </div>
                <div style={{display: "flex", gap: 10}}>
                    <input style={{
                        borderRadius: 5,
                        borderColor: 'forestgreen',
                        outline: 'none',
                        border: 'none',
                        background: '#2DAA944F',
                    }} placeholder="Enter Subject Name" type="text" value={filterObj.subject}
                           onChange={(e) => setFilterObj({type: filterObj.type, subject: e.target.value})}/>
                    <select style={{
                        borderRadius: 5,
                        borderWidth: 3,
                        borderColor: 'forestgreen',
                        fontSize: 14,
                        outline: 'none',
                        border: 'none',
                        backgroundColor: '#2DAA944F',
                    }}
                            value={filterObj.type}
                            onChange={(e) => setFilterObj({type: e.target.value, subject: filterObj.subject})}>
                        {postTypes.map(type => (
                            <option value={type.value}>{type.name}</option>
                        ))}
                    </select>
                    <button onClick={handleFilterButton} style={{
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
                    }}> Filter
                    </button>
                    <button onClick={handleAddPost} style={{
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
                </div>
            </div>
            {showAddPost &&
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
                    <div style={{display: "flex", justifyContent: "space-between", width: "100%"}}>
                        <input style={{
                            flex: 1,
                            borderWidth: 2,
                            borderRadius: 4,
                            outline: 'none',
                            borderColor: 'forestgreen'
                        }}
                               type="text" value={subject} onChange={e => setSubject(e.target.value)}
                               placeholder="Enter your subject here"/>

                        <select style={{
                            flex: 1,
                            marginLeft: 10,
                            borderWidth: 2,
                            borderRadius: 4,
                            borderColor: 'forestgreen',
                            fontSize: 14,
                            outline: 'none'
                        }} value={postType} onChange={(e) => setPostType(e.target.value)}>
                            {postTypes.map(type => (
                                <option value={type.value}>{type.name}</option>
                            ))}
                        </select>

                        <button style={{
                            flex: 1,
                            marginLeft: 10,
                            backgroundColor: '#2DAA94',
                            outline: "none",
                            border: "none",
                            paddingTop: 10,
                            paddingBottom: 10,
                            paddingLeft: 15,
                            paddingRight: 15,
                            borderRadius: 32,
                            textAlign: "center",
                            fontSize: 14,
                            color: '#FFFFFF',
                            cursor: "pointer",
                            boxShadow: 'none'
                        }} onClick={handleSubmit}>submit
                        </button>
                    </div>
                    <textarea style={{
                        marginTop: 10,
                        width: "100%",
                        borderWidth: 2,
                        borderRadius: 4,
                        borderColor: 'forestgreen',
                        fontSize: 14,
                        boxShadow: 'none',
                        outline: 'none'
                    }} type="textarea" value={content} onChange={e => setContent(e.target.value)}
                              placeholder="Enter your text"/>
                </div>
            }

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
                            <div
                                style={{
                                    display: "flex",
                                    alignItems: "center",
                                    gap: 12,
                                }}>
                                <div style={{width: 32, height: 32, borderRadius: 16, backgroundColor: "black"}}/>
                                <div style={{display: "flex", flexDirection: "column"}}>
                                    <span style={{fontWeight: "500", color: "#506C68"}}>{a.username}</span>
                                    <span style={{
                                        fontSize: 12,
                                        color: '#2DAA94'
                                    }}>{a.subject} - {a.type.charAt(0) + a.type.substring(1).toLowerCase()}</span></div>
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
                        }}>
                        </button>
                    </>
                ))}

            </div>
        </div>
    )
}

export default StudentForum