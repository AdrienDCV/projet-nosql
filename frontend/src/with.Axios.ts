import React, {useEffect} from 'react';
import axios from 'axios';

const WithAxios = (el: { children: React.JSX.Element }): React.JSX.Element => {
  // const {signOut} = useAuthentication();

  useEffect(() => {
    const requestInterceptor = axios.interceptors.request.use(async config => {
      const token: string | null = localStorage.getItem('token');

      config.headers['Content-Type'] = 'application/json;charset=UTF-8';
      config.headers['Source'] = 'dashboard';

      if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
      }

      return config;
    });

    return () => {
      axios.interceptors.request.eject(requestInterceptor);
    };
  }, []);

  return el.children;
};

export default WithAxios;