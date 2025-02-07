import SongContextProvider from "./components/context/SongContext";
import SongList from "./components/song/List";
import SongForm from "./components/song/Form";

function App() {
  return (
    <div className="App">
        <SongContextProvider>
            <SongList />
            <SongForm />
        </SongContextProvider>
    </div>
  );
}

export default App;
