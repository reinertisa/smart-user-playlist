import {useContext} from 'react';
import {AuthContext} from "./context/AuthContext";
import {ThemeContext} from "./context/ThemeContext";

export default function Navbar() {
    const {isAuthenticated, toggleAuth} = useContext(AuthContext);
    const {isLightTheme, darkMode, lightMode} =  useContext(ThemeContext);
    const theme = isLightTheme ? lightMode : darkMode;
    return (
        <nav style={{background: theme.ui, color: theme.syntax}}>
            <h1>Smart Playlist</h1>
            <div onClick={toggleAuth}>{isAuthenticated ? 'Logged in' : 'Logged out'}</div>
            <ul>
                <li>Home</li>
                <li>About</li>
                <li>Contact</li>
            </ul>
        </nav>
    )
}