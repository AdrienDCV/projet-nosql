import React from "react";
import LoginIcon from '@mui/icons-material/Login';
import LogoutIcon from '@mui/icons-material/Logout';
import ShoppingBagIcon from '@mui/icons-material/ShoppingBag';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import FormatListBulletedIcon from '@mui/icons-material/FormatListBulleted';
import ApartmentIcon from '@mui/icons-material/Apartment';
import { Warehouse } from "@mui/icons-material";
import { useAuthentication } from "../../hooks/authentication-context.hook.tsx";
import { signOutClient, signOutProducer } from "../../services/user.service.tsx";
import { useNavigate } from "react-router-dom";
import { UserType } from "../../models/enum/user-type.enum.ts";

export function NavbarComponent(): React.JSX.Element {
  const { isAuthenticated, setIsAuthenticated, setUser, setAuthToken, user } = useAuthentication();
  const navigate = useNavigate();

  const handleSignOut = async () => {
    try {
      if (user?.userType === UserType.PRODUCER) {
        await signOutProducer();
      } else {
        await signOutClient();
      }
      setAuthToken(undefined);
      setUser(undefined);
      setIsAuthenticated(false);
      localStorage.removeItem("token");
      localStorage.removeItem("user");
    } catch (error) {
      console.error("Erreur de déconnexion :", error);
    }
  };

  return (
      <nav className="grid grid-cols-3 items-center px-6 h-17.5 rounded-b-3xl bg-[#FFE4B9]">

        {/* GAUCHE */}
        <div className="flex justify-start">
          {isAuthenticated ? (
              <button onClick={handleSignOut} className="flex flex-col items-center">
                <LogoutIcon className="text-secondary text-2xl" />
                <span className="text-secondary text-xs">Déconnexion</span>
              </button>
          ) : (
              <button onClick={() => navigate("/sign-in")} className="flex flex-col items-center">
                <LoginIcon className="text-secondary text-2xl" />
                <span className="text-secondary text-xs">Connexion</span>
              </button>
          )}
        </div>

        {/* CENTRE */}
        <div className="flex justify-center">
          <a href="/home">
            <img
                src="src/assets/images/Logo.png"
                alt="Logo"
                className="w-12 h-12 object-contain"
            />
          </a>
        </div>

        <div className="flex justify-end gap-6">
          <div className="flex flex-col items-center">
            <a href="/order-history">
              <FormatListBulletedIcon className="text-[#FF5733] text-2xl" />
            </a>
            <span className="text-[#FF5733] text-xs font-bold">Historique</span>
          </div>

          {user?.userType === UserType.CLIENT && (
              <div className="flex flex-col items-center">
                <a href="/cart">
                  <ShoppingBagIcon className="text-[#FF5733] text-2xl" />
                </a>
                <span className="text-[#FF5733] text-xs font-bold">Panier</span>
              </div>
          )}

          {user?.userType === UserType.PRODUCER && (
              <>
                <div className="flex flex-col items-center">
                  <a href="/producer/inventory">
                    <Warehouse className="text-[#FF5733] text-2xl" />
                  </a>
                  <span className="text-[#FF5733] text-xs font-bold">Inventaire</span>
                </div>

                <div className="flex flex-col items-center">
                  <a href="/business">
                    <ApartmentIcon className="text-[#FF5733] text-2xl" />
                  </a>
                  <span className="text-[#FF5733] text-xs font-bold text-center">
                Boutique
              </span>
                </div>
              </>
          )}

          <div className="flex flex-col items-center">
            <a href="/profile">
              <AccountCircleIcon className="text-[#FF5733] text-2xl" />
            </a>
            <span className="text-[#FF5733] text-xs font-bold">Profil</span>
          </div>
        </div>
      </nav>
  );
}
