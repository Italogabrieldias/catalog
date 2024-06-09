import Navbar from "components/Navbar";
import Admin from "page/Admin";
import Catalog from "page/Catalog";
import Home from "page/Home";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

export const Routers = () => {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/catalog' element={<Catalog />} />
        <Route path='/admin' element={<Admin />} />
      </Routes>
    </Router>
  );
};
export default Routers;