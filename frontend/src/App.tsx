import { Suspense } from "react";
import './App.css'
import {HomePage} from "./pages/home/home.page.tsx";
import {Navigate, Route, Routes} from "react-router";
import {FooterComponent} from "./components/footer/footer.component.tsx";
import {BasketPage} from "./pages/basket/basket.page.tsx";
import ProtectedRoute from "./components/protected-route/protected-route.component.tsx";
import {SignUpPage} from "./pages/authentication/sign-up/sign-up.page.tsx";
import {SignInPage} from "./pages/authentication/sign-in/sign-in.page.tsx";
import {CreateBusinessPage} from "./pages/create-business/create-business.page.tsx";
import {ProfilePage} from "./pages/profile/profile.page.tsx";
import {ProductsPage} from "./pages/products/products.page.tsx";
import {OrderListPage} from "./pages/order-list/order-list.page.tsx";
import {ClientOrderHistoryPage} from "./pages/order-details/client-order-history.page.tsx";

function App() {

  return (
    <div className="w-full h-full flex flex-col gap-8">
      <Suspense fallback={<h1>Page is loading...</h1>}>
        <Routes>
          <Route element={<ProtectedRoute />}>
            <Route path='/home' element={<HomePage />} />
            <Route path='/create-business' element={<CreateBusinessPage />} />
            <Route path='/order-list' element={<OrderListPage />} />
            <Route path='/cart' element={<BasketPage />} />
            <Route path='/profile' element={<ProfilePage />} />
            <Route path='/client-order-history' element={<ClientOrderHistoryPage />} />
            <Route index path='/products' element={<ProductsPage />} />
          </Route>
          <Route path='/sign-up' element={<SignUpPage />} />
          <Route path='/sign-in' element={<SignInPage />} />
          <Route path='*' element={<Navigate to='/home' />} />
        </Routes>
      </Suspense>
      <FooterComponent/>
    </div>
  )
}

export default App
