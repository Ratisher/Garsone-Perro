import './app.css'
import EnterComponent from './Components/EnterComponent'
import FooterComponents from './Components/FooterComponents'
import HeaderComponent from './Components/HeaderComponent'
import GameComponent from './Components/GameComponent'
import GameMenuComponent from './Components/GameMenuComponent'
import { BrowserRouter, Routes, Route } from "react-router-dom"
import { useState } from 'react';

export function App() {

  const [id, setId] = useState('');

  const handleChange = (NewId) => {
      setId(NewId);
  }

  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          {/* // http://localhost:5173/ */}
          <Route path='/' element={<EnterComponent onChange={handleChange} />}></Route>
          {/* // http://localhost:5173/game */}
          <Route path='/game' element={<GameComponent />}></Route>
          {/* // http://localhost:5173/menu */}
          <Route path='/menu' element={<GameMenuComponent id={id}/>}></Route>
        </Routes>
        <FooterComponents />
      </BrowserRouter>
    </>
  )
}
