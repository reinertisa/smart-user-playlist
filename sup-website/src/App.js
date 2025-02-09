import SongContextProvider from "./components/context/SongContext";
import SongList from "./components/song/List";
import SongForm from "./components/song/Form";
import Navbar from "./components/Navbar";
import AuthContextProvider from "./components/context/AuthContext";
import ThemeContextProvider from "./components/context/ThemeContext";
import ThemeToggle from "./components/ThemeToggle";

function App() {
  return (
      <div className="App">
          <ThemeContextProvider>
              <SongContextProvider>
                  <AuthContextProvider>
                      <Navbar />
                  </AuthContextProvider>
                  <SongList />
                  <SongForm />
                  <ThemeToggle />
              </SongContextProvider>
          </ThemeContextProvider>
      </div>
  );
}

export default App;
