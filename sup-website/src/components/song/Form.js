import {useContext, useState} from 'react';
import {SongContext} from "../context/SongContext";

export default function SongForm() {
    const {dispatchSongs} = useContext(SongContext);
    const [artist, setArtist] = useState('');
    const [title, setTitle] = useState('');

    const handleArtistChange = (evt) => setArtist(evt.target.value);
    const handleTitleChange = (evt) => setTitle(evt.target.value);

    const handleSubmit = (evt) => {
        evt.preventDefault();

        dispatchSongs({type: 'ADD_SONG', artist, title});
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

            <input type="submit" value="Save" />
        </form>
    )
}