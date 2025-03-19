import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Components/Login/Login";
import Signup from "./Components/Registro/Signup";
import './App.css'

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/registro" element={<Signup />} />
            </Routes>
        </Router>
    );
}

export default App;

