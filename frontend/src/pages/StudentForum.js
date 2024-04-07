import React, {useState} from 'react';
import '../styles/StudentForum.css';

const StudentForum = () => {
    // hardcoded data for posts and comments
    const [posts, setPosts] = useState([
        {
            id: 1,
            author: 'John Doe',
            content: 'Lorem ipsum dolor sit amet.',
            comments: [{id: 1, author: 'Alice Smith', content: 'Nice post!'}, {
                id: 2,
                author: 'Bob Johnson',
                content: 'Great job!'
            }]
        },
        {
            id: 2,
            author: 'Alice Smith',
            content: 'Consectetur adipiscing elit.',
            comments: [{id: 1, author: 'John Doe', content: 'Interesting topic.'}]
        },
    ]);

    const [selectedPost, setSelectedPost] = useState(null);
    const [comment, setComment] = useState('');

    const handlePostClick = postId => {
        setSelectedPost(postId);
    };

    const handleCommentChange = event => {
        setComment(event.target.value);
    };

    const handleAddComment = () => {
        if (comment.trim() !== '') {
            const updatedPosts = posts.map(post =>
                post.id === selectedPost
                    ? {
                        ...post,
                        comments: [...post.comments, {id: post.comments.length + 1, author: 'You', content: comment}]
                    }
                    : post
            );
            setPosts(updatedPosts);
            setComment('');
        }
    };

    return (
        <div className="student-forum-container">
            <h2>Student Forum</h2>
            <div className="posts-list">
                {posts.map(post => (
                    <div key={post.id} className="post-item" onClick={() => handlePostClick(post.id)}>
                        <h3>{post.author}</h3>
                        <p>{post.content}</p>
                        <p><strong>Comments:</strong> {post.comments.length}</p>
                    </div>
                ))}
            </div>
            {selectedPost !== null && (
                <div className="comments-section">
                    <h3>Comments</h3>
                    {posts.find(post => post.id === selectedPost).comments.map((comment, index) => (
                        <div key={comment.id} className="comment-item">
                            <p><strong>{comment.author}:</strong> {comment.content}</p>
                        </div>
                    ))}
                    <div className="comment-form">
                        <input
                            type="text"
                            placeholder="Write your comment"
                            value={comment}
                            onChange={handleCommentChange}
                        />
                        <button onClick={handleAddComment}>Add Comment</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default StudentForum;