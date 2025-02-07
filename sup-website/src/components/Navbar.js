import {useContext} from 'react';
import {AuthContext} from "./context/AuthContext";

export default function Navbar() {
    const {isAuthenticated} = useContext(AuthContext);
    return (
        <nav>
            <h1>Smart Playlist</h1>
            <div>{isAuthenticated ? 'Logged in' : 'Logged out'}</div>
            <ul>
                <li>Home</li>
                <li>About</li>
                <li>Contact</li>
            </ul>
        </nav>
    )
}