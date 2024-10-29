import './app.css'
import EnterComponent from './Components/EnterComponent'
import FooterComponent from './Components/FooterComponent'
import HeaderComponent from './Components/HeaderComponent'
import GameMenuComponent from './Components/GameMenuComponent'
import GameMapGarson from './Components/GameMapGarsone'
import GameMapPerro from './Components/GameMapPerro'
import { BrowserRouter, Routes, Route } from "react-router-dom"

export function App() {

  return (
    <>
      <BrowserRouter>
      {location.pathname !== '/gamestartesFG' && location.pathname !== '/gamestartesFP' && <HeaderComponent />}
        <Routes>
          {/* // http://localhost:5173/ */}
          <Route path='/' element={<EnterComponent />}></Route>
          {/* // http://localhost:5173/menu */}
          <Route path='/menu' element={<GameMenuComponent />}></Route>
          {/* // http://localhost:5173/gamestartesFG */}
          <Route path='/gamestartesFG' element={<GameMapGarson />}></Route>
          {/* // http://localhost:5173/gamestartesFP */}
          <Route path='/gamestartesFP' element={<GameMapPerro />}></Route>
        </Routes>
        {location.pathname !== '/gamestartesFG' && location.pathname !== '/gamestartesFP' && <FooterComponent />}
      </BrowserRouter>
    </>
  )
}
