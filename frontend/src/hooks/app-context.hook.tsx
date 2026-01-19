import React from "react";
import {AppContext, type AppContextType} from "../context/app.context.tsx";

export function useApp(): AppContextType {
  return React.useContext(AppContext) as AppContextType;
}