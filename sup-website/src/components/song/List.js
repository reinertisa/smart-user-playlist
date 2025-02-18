import {useContext, useEffect, useState} from 'react';
import SongDetails from "./Details";
import {ThemeContext} from "../context/ThemeContext";

export default function SongList() {
    const {isLightTheme, darkMode, lightMode} = useContext(ThemeContext);
    const theme = isLightTheme ? lightMode : darkMode;
    const [songs, setSongs] = useState([]);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        let mount = true;
        const loadData = async () => {
            try {
                const rez = await fetch('http://localhost:8081/api/v1/songs');
                if (!rez.ok) {
                    throw Error('Failed to fetching data');
                }
                if (mount) {
                    setSongs(await rez.json());
                    setLoading(false);
                    setError('');
                }
            } catch (err) {
                if (mount) {
                    setError(err.message);
                    setLoading(false);
                }
            }
        }

        loadData();

        return () => {
            mount = false;
        }
    }, []);

    const handleDelete = async (id) => {
        try {
            await fetch(`http://localhost:8081/api/v1/songs/${id}`, {
                method: 'DELETE',
            });
            setSongs(songs.filter(song => song?.songId !== id));
        } catch (err) {
            setError(err.message);
        }
    }

    let body = loading ? <div>Loading...</div> : null;
    if (songs) {
        console.log('songs', songs);
        body = songs?.length > 0 ? (
            <div className="song-list" style={{color: theme.syntax, background: theme.bg}}>
                <ul>
                    {songs.map(song => (
                        <SongDetails key={song?.songId} song={song} onDelete={handleDelete} />
                    ))}
                </ul>
            </div>
        ) : (
            <div className="empty">No song found on the list!</div>
        )
    } else if (error) {
        body = <div>{error}</div>
    }

    return body;
}
