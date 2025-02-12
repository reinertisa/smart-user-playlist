import {useContext} from 'react';
import {AuthContext} from "./context/AuthContext";
import {ThemeContext} from "./context/ThemeContext";
import {Link} from "react-router";

export default function Navbar() {
    const {isAuthenticated, toggleAuth} = useContext(AuthContext);
    const {isLightTheme, darkMode, lightMode} =  useContext(ThemeContext);
    const theme = isLightTheme ? lightMode : darkMode;
    return (
        <nav className="navbar" style={{background: theme.ui, color: theme.syntax}}>
            <h1>Smart Playlist</h1>

            <ul>
                <li><Link to="/">Song List</Link></li>
                <li><Link to="/create">Add song</Link></li>
                <li onClick={toggleAuth}>{isAuthenticated ? 'Logged in' : 'Logged out'}</li>
            </ul>
        </nav>
    )
}