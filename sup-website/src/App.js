import SongContextProvider from "./components/context/SongContext";
import SongList from "./components/song/List";
import SongForm from "./components/song/Form";
import Navbar from "./components/Navbar";
import AuthContextProvider from "./components/context/AuthContext";
import ThemeContextProvider from "./components/context/ThemeContext";

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
              </SongContextProvider>
          </ThemeContextProvider>
      </div>
  );
}

export default App;
