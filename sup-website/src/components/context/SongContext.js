import {createContext, useReducer} from 'react';
import songReducer from "../reducer/songReducer";

export const SongContext = createContext();

export default function SongContextProvider({children}) {

    const [songs, dispatchSongs] = useReducer(songReducer, []);

    return (
        <SongContext.Provider value={{songs, dispatchSongs}}>
            {children}
        </SongContext.Provider>
    );
}