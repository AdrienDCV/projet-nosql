import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import WithAxios from "./with.Axios.ts";
import {BrowserRouter} from "react-router";
import {AuthenticationContextProvider} from "./context/authentication.context.tsx";
import AppContextProvider from "./context/app.context.tsx";

createRoot(document.getElementById('root')!).render(
    <BrowserRouter>
      <StrictMode>
        <AuthenticationContextProvider>
          <WithAxios>
            <AppContextProvider>
              <App />
            </AppContextProvider>
          </WithAxios>
        </AuthenticationContextProvider>
      </StrictMode>
    </BrowserRouter>
)
