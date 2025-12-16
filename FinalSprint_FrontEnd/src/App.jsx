import './App.css'

import axios from 'axios'
import Navigation from './components/Navigation'
import Home from './components/Home'
import PreviousBSTs from './components/PreviousBSTs'
import AddBST from './components/AddBST'
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {

  return (
    <>
    <BrowserRouter>
      <Navigation />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/previous-trees" element={<PreviousBSTs />} />
        <Route path="/enter-numbers" element={<AddBST />} />
      </Routes>
    </BrowserRouter>
    </>
  )
}

export default App
