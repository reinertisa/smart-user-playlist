import SongContextProvider from "./components/context/SongContext";
import SongList from "./components/song/List";
import SongForm from "./components/song/Form";
import Navbar from "./components/Navbar";
import AuthContextProvider from "./components/context/AuthContext";
import ThemeContextProvider from "./components/context/ThemeContext";
import {BrowserRouter, Route, Routes} from "react-router";
import LoginForm from "./components/user/Login";
import RegisterForm from "./components/user/Register";

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
                      <Route path="/login" element={<LoginForm />} />
                      <Route path="/register" element={<RegisterForm />} />
                  </Routes>
              </SongContextProvider>
          </ThemeContextProvider>
          </BrowserRouter>
      </div>
  );
}

export default App;
