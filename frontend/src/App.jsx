import { Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/auth/Login/Login";
import Register from "./pages/auth/Register/Register";

function App() {
  return (
    <Routes>
      {/* Default page */}
      <Route path="/" element={<Navigate to="/login" />} />

      {/* Pages */}
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
    </Routes>
  );
}

export default App;