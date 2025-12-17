import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import WithAxios from "./with.Axios.ts";
import {BrowserRouter} from "react-router";

createRoot(document.getElementById('root')!).render(
    <BrowserRouter>
      <StrictMode>
        <WithAxios>
          <App />
        </WithAxios>
      </StrictMode>
    </BrowserRouter>
)
