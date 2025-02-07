import {useContext} from 'react'
import {SongContext} from "../context/SongContext";

export default function SongDetails({song}) {
    const {dispatchSongs} = useContext(SongContext);

    return (
        <li onClick={() => dispatchSongs({type: 'REMOVE_SONG', id: song.id})}>
            <div className="artist">{song.artist}</div>
            <div className="title">{song.title}</div>
        </li>
    )
}