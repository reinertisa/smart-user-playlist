import {useContext, useState} from 'react';
import {SongContext} from "../context/SongContext";

export default function SongForm() {
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
            const rez = await fetch('http://localhost:8080/api/v1/songs', {
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
    }

    return (
        <form onSubmit={handleSubmit}>
            <label htmlFor="artist">Artist</label>
            <input
                id="artist"
                type="text"
                value={artist}
                onChange={handleArtistChange}
                required={true}
                placeholder="Song title"
            />

            <input
                id="title"
                type="text"
                value={title}
                onChange={handleTitleChange}
                required={true}
                placeholder="artist"
            />
            <input disabled={loading} type="submit" value="Save" />
            {err && <p style={{color: 'red'}}>{err.message}</p> }
        </form>
    )
}