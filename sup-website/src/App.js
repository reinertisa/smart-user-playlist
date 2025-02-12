import SongContextProvider from "./components/context/SongContext";
import SongList from "./components/song/List";
import SongForm from "./components/song/Form";
import Navbar from "./components/Navbar";
import AuthContextProvider from "./components/context/AuthContext";
import ThemeContextProvider from "./components/context/ThemeContext";
import ThemeToggle from "./components/ThemeToggle";
import {BrowserRouter, Route, Routes} from "react-router";

function App() {
  return (
      <div className="App">
          <BrowserRouter>
          <ThemeContextProvider>
              <SongContextProvider>
                  <AuthContextProvider>
                      <Navbar />
                  </AuthContextProvider>
                  <Routes>
                      <Route path="/" element={<SongList />} />
                      <Route path="/create" element={<SongForm />} />
                  </Routes>
                  <ThemeToggle />
              </SongContextProvider>
          </ThemeContextProvider>
          </BrowserRouter>
      </div>
  );
}

export default App;
