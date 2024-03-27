import { Routes, Route } from "react-router-dom";
import "./App.css";
import { Navigation } from "./components/Navigation/Navigation";
import { Home } from "./components/Home/Home";
import { AuthProvider } from "./contexts/AuthContext";
import { Wrapper } from "./components/Wrapper/Wrapper";

function App() {
  const lang = localStorage.getItem("lang");
  return (
    <>
      <AuthProvider>
        <Navigation />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='users/login' element={<Wrapper />} />
        </Routes>
      </AuthProvider>
    </>
  );
}

export default App;
