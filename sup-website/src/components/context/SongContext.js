import {createContext, useEffect, useReducer} from 'react';
import songReducer from "../reducer/songReducer";

export const SongContext = createContext();

export default function SongContextProvider({children}) {

    const [songs, dispatchSongs] = useReducer(songReducer, [], () => {
        const localData = window.localStorage.getItem('songs');
        if (localData) {
            return JSON.parse(localData);
        }
        return [];
    });

    useEffect(() => {
        window.localStorage.setItem('songs', JSON.stringify(songs));
    }, [songs]);

    return (
        <SongContext.Provider value={{songs, dispatchSongs}}>
            {children}
        </SongContext.Provider>
    );
}