import {useContext} from 'react'
import {SongContext} from "../context/SongContext";
import {ThemeContext} from "../context/ThemeContext";

export default function SongDetails({song}) {
    const {dispatchSongs} = useContext(SongContext);
    const {isLightTheme, darkMode, lightMode} = useContext(ThemeContext);
    const theme = isLightTheme ? lightMode : darkMode;

    return (
        <li style={{background: theme.ui}} onClick={() => dispatchSongs({type: 'REMOVE_SONG', id: song.id})}>
            <div className="artist">{song.artist}</div>
            <div className="title">{song.title}</div>
        </li>
    )
}