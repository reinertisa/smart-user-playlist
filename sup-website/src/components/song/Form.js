import {useContext, useState} from 'react';
import {SongContext} from "../context/SongContext";
import {useNavigate} from "react-router";

export default function SongForm() {
    const navigate = useNavigate()
    const {dispatchSongs} = useContext(SongContext);
    const [artist, setArtist] = useState('');
    const [title, setTitle] = useState('');
    const [err, setErr] = useState('');
    const [loading, setLoading] = useState(false);

    const handleArtistChange = (evt) => setArtist(evt.target.value);
    const handleTitleChange = (evt) => setTitle(evt.target.value);

    const handleSubmit = async (evt) => {
        evt.preventDefault();

        try {
            setLoading(true);
            const rez = await fetch('http://localhost:8081/api/v1/songs', {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: JSON.stringify({
                    title,
                    artist,
                })
            });

            if (!rez.ok) {
                throw new Error('Something went wrong');
            }
            const json = await rez.json();
            dispatchSongs({type: 'ADD_SONG', ...json});
            setLoading(false);
        } catch (err) {
            setLoading(false);
            setErr(err);
        }
        setTitle('');
        setArtist('');
        navigate("/");
    }

    return (
        <div className="create">
            <h2>Add a New Song</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="artist">Artist</label>
                <input
                    id="artist"
                    type="text"
                    value={artist}
                    onChange={handleArtistChange}
                    required={true}
                />
                <label htmlFor="title">Title</label>
                <input
                    id="title"
                    type="text"
                    value={title}
                    onChange={handleTitleChange}
                    required={true}
                />
                <button disabled={loading} type="submit">Save</button>
                {err && <p style={{color: 'red'}}>{err.message}</p> }
            </form>
        </div>
    );
}