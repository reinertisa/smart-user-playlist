import {createContext, useState} from 'react';

export const ThemeContext = createContext(null);

export default function ThemeContextProvider({ children }) {
    const [isLightTheme, setIsLightTheme] = useState(true);
    const darkMode = {
        syntax: '#ffffff',
        ui: '#ddd',
        bg: '#040303'
    };

    const lightMode = {
        syntax: '#040303',
        ui: '#333',
        bg: '#555'
    };

    const toggleTheme = () => setIsLightTheme(!isLightTheme);


    return (
        <ThemeContext.Provider value={{isLightTheme, darkMode, lightMode, toggleTheme}}>
            {children}
        </ThemeContext.Provider>
    );
}