import {useContext} from 'react';
import {SongContext} from "../context/SongContext";
import SongDetails from "./Details";

export default function SongList() {
    const {songs} = useContext(SongContext);

    return songs?.length > 0 ? (
        <div className="song-list">
            <ul>
                {songs.map(song => (
                    <SongDetails key={song.id} song={song} />
                ))}
            </ul>
        </div>
    ) : (
        <div className="empty">No song found on the list!</div>
    )
}
