import {v4 as uuid} from 'uuid';

export default function songReducer(songs, action) {
    switch (action.type) {
        case 'ADD_SONG':
            return [...songs, {songId: action.songId || uuid(), artist: action.artist, title: action.title}];
        case 'REMOVE_SONG':
            return songs.filter(song => song.songId !== action.songId);
        default:
            return songs;
    }
}