import { Routes, Route } from "react-router-dom";
import "./App.css";

import { Navigation } from "./components/Navigation/Navigation";
import { Home } from "./components/Home/Home";

function App() {
  return (
    <>
      <Navigation />
      <Routes>
        <Route path='/' element={<Home />} />
      </Routes>
    </>
  );
}

export default App;
