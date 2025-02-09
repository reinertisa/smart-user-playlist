import {useContext} from 'react';
import {SongContext} from "../context/SongContext";
import SongDetails from "./Details";
import {ThemeContext} from "../context/ThemeContext";

export default function SongList() {
    const {songs} = useContext(SongContext);
    const {isLightTheme, darkMode, lightMode} = useContext(ThemeContext);
    const theme = isLightTheme ? lightMode : darkMode;

    return songs?.length > 0 ? (
        <div className="song-list" style={{color: theme.syntax, background: theme.bg}}>
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
