import {Navigate, Outlet, type RouteProps, useLocation} from 'react-router';
import React from 'react';
import {useAuthentication} from "../../hooks/authentication-context.hook.tsx";
import {CircularProgress} from "@mui/material";
import {NavbarComponent} from "../navbar/navbar.component.tsx";
import {UserType} from "../../models/enum/user-type.enum.ts";

type ProtectedRouteProps = RouteProps & {
  redirectPath?: string;
};

const ProtectedRoute = ({
                          redirectPath,
                          children,
                        }: ProtectedRouteProps): React.JSX.Element => {
  const { isAuthenticated, isInitializing, user, isBusinessCreated } = useAuthentication();
  const location = useLocation();

  if (isInitializing) {
    return (
        <div className='flex flex-col justify-center items-center h-full w-full'>
          <CircularProgress />
        </div>
    );
  }

  if (!isAuthenticated && !isInitializing) {
    return (
        <Navigate
            to={redirectPath ?? '/sign-in'}
            state={{ from: location }}
            replace
        />
    );
  }

  if (isAuthenticated && user?.userType === UserType.PRODUCER && !isBusinessCreated && location.pathname !== '/create-business') {
    return (
        <Navigate
            to="/create-business"
            replace
        />
    );
  }

  return (
      <>
        <NavbarComponent />
        <div className='flex flex-col h-full items-center pt-[53px] overflow-auto '>
          <div className=' flex flex-col w-full flex-1 bg-logo-background bg-no-repeat bg-right-top'>
            {children ? children as React.JSX.Element : <Outlet />}
          </div>
        </div>
      </>
  );
};

export default ProtectedRoute;