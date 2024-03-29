import { Routes, Route } from "react-router-dom";
import "./App.css";
import { Navigation } from "./components/Navigation/Navigation";
import { Home } from "./components/Home/Home";
import { AuthProvider } from "./contexts/AuthContext";
import { WrapperLogin } from "./components/Wrapper/WrapperLogin";
import { Logout } from "./components/Logout/Logout";

function App() {
  const lang = localStorage.getItem("lang");
  return (
    <>
      <AuthProvider>
        <Navigation />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='users/login' element={<WrapperLogin />} />
          <Route path='users/logout' element={<Logout />} />
        </Routes>
      </AuthProvider>
    </>
  );
}

export default App;
