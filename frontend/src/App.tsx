import { Suspense } from "react";
import './App.css'
import {HomePage} from "./pages/home/home.page.tsx";
import {Route, Routes} from "react-router";
import {FooterComponent} from "./components/footer/footer.component.tsx";
import {NavbarComponent} from "./components/navbar/navbar.component.tsx";
import {BasketPage} from "./pages/basket/basket.page.tsx";

function App() {

  return (
    <div className="w-full h-full flex flex-col gap-8">
      <NavbarComponent/>
      <Suspense fallback={<h1>Page is loading...</h1>}>
        <Routes>
          <Route index path='/home' element={<HomePage />} />
          <Route index path='/basket' element={<BasketPage />} />
        </Routes>
      </Suspense>
      <FooterComponent/>
    </div>
  )
}

export default App
