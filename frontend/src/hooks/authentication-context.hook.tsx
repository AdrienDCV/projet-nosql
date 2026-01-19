import {AuthenticationContext, type AuthenticationContextType} from "../context/authentication.context.tsx";
import React from "react";

export function useAuthentication(): AuthenticationContextType {
  return React.useContext(AuthenticationContext) as AuthenticationContextType;
}