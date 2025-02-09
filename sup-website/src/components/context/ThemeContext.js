import {createContext, useState} from 'react';

export const ThemeContext = createContext(null);

export default function ThemeContextProvider({ children }) {
    const [isLightTheme, setIsLightTheme] = useState(true);
    const darkMode = {
        syntax: '#eee',
        ui: '#333',
        bg: '#eee'
    };

    const lightMode = {
        syntax: '#eee',
        ui: '#333',
        bg: '#eee'
    };

    const toggleTheme = () => setIsLightTheme(!isLightTheme);


    return (
        <ThemeContext.Provider value={{darkMode, lightMode, toggleTheme}}>
            {children}
        </ThemeContext.Provider>
    );
}