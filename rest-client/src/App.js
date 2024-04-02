import { Routes, Route } from "react-router-dom";
import "./App.css";
import { Navigation } from "./components/Navigation/Navigation";
import { Home } from "./components/Home/Home";
import { AuthProvider } from "./contexts/AuthContext";
import { WrapperLogin } from "./components/Wrapper/WrapperLogin";
import { Logout } from "./components/Logout/Logout";
import { useEffect } from "react";
import { FamilyProvider } from "./contexts/FamilyContext";
import { Families } from "./components/Family/Families";

function App() {
  const lang = localStorage.getItem("lang");
  return (
    <>
      <AuthProvider>
        <FamilyProvider>
          <Navigation />
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='users/login' element={<WrapperLogin />} />
            <Route path='users/logout' element={<Logout />} />
            <Route path='family' element={<Families />} />
          </Routes>
        </FamilyProvider>
      </AuthProvider>
    </>
  );
}

export default App;
