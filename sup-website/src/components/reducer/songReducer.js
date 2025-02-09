import {v4 as uuid} from 'uuid';

export default function songReducer(songs, action) {
    switch (action.type) {
        case 'ADD_SONG':
            return [...songs, {id: action.songId || uuid(), artist: action.artist, title: action.title}];
        case 'REMOVE_SONG':
            return songs.filter(song => song.id !== action.id);
        default:
            return songs;
    }
}