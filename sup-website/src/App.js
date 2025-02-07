import SongContextProvider from "./components/context/SongContext";
import SongList from "./components/song/List";
import SongForm from "./components/song/Form";
import Navbar from "./components/Navbar";
import AuthContextProvider from "./components/context/AuthContext";

function App() {
  return (
    <div className="App">
        <SongContextProvider>
            <AuthContextProvider>
                <Navbar />
            </AuthContextProvider>
            <SongList />
            <SongForm />
        </SongContextProvider>
    </div>
  );
}

export default App;
