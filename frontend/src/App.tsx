import { Suspense } from "react";
import './App.css'
import {HomePage} from "./pages/home/home.page.tsx";
import {Navigate, Route, Routes} from "react-router";
import {FooterComponent} from "./components/footer/footer.component.tsx";
import {CartPage} from "./pages/cart/cart.page.tsx";
import ProtectedRoute from "./components/protected-route/protected-route.component.tsx";
import {SignUpPage} from "./pages/authentication/sign-up/sign-up.page.tsx";
import {SignInPage} from "./pages/authentication/sign-in/sign-in.page.tsx";
import {CreateBusinessPage} from "./pages/create-business/create-business.page.tsx";
import {UpdateBusinessPage} from "./pages/update-business.page.tsx/update-business.page.tsx";
import {ProfilePage} from "./pages/profile/profile.page.tsx";
import {ProductsPage} from "./pages/products/products.page.tsx";
import {ProductDetailsPage} from "./pages/products/product-details.page.tsx";
import {ClientOrderHistoryPage} from "./pages/order-history/client-order-history.page.tsx";
import {CreateProductsPage} from "./pages/create-products/create-porducts.page.tsx";
import {ProducerInventoryPage} from "./pages/producer-inventory/producer-inventory.page.tsx";
import {ProductUpdatesPage} from "./pages/products/product-update.page.tsx";
import {ProducerOrderHistoryPage} from "./pages/order-history/producer-order-history.page.tsx";
import {ClientOrderDetailsPage} from "./pages/order-details/client-order-details.page.tsx";

function App() {

  return (
    <div className="w-full h-full flex flex-col">
      <Suspense fallback={<h1>Page is loading...</h1>}>
        <Routes>
          <Route element={<ProtectedRoute />}>
            <Route path='/home' element={<HomePage />} />
            <Route path='/create-business' element={<CreateBusinessPage />} />
            <Route path='/business' element={<UpdateBusinessPage />} />
            <Route path='/cart' element={<CartPage />} />
            <Route path='/profile' element={<ProfilePage />} />
            <Route path='/client/order-history' element={<ClientOrderHistoryPage />} />
            <Route path='/producer/order-history' element={<ProducerOrderHistoryPage />} />
            <Route path='/client-order/details/:clientOrderId' element={<ClientOrderDetailsPage />} />
            <Route index path='/products' element={<ProductsPage />} />
            <Route index path='/product-details/:productId' element={<ProductDetailsPage />} />
            <Route path='/create-product' element={<CreateProductsPage />}/>
            <Route path='/producer/inventory' element={<ProducerInventoryPage/>}/>
            <Route path='/product-update/:productId' element={<ProductUpdatesPage/>}/>
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
