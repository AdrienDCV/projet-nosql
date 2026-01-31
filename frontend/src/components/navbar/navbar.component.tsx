import React from "react";
import LoginIcon from '@mui/icons-material/Login';
import LogoutIcon from '@mui/icons-material/Logout';
import ShoppingBagIcon from '@mui/icons-material/ShoppingBag';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import FormatListBulletedIcon from '@mui/icons-material/FormatListBulleted';
import {useAuthentication} from "../../hooks/authentication-context.hook.tsx";
import {signOutClient, signOutProducer} from "../../services/user.service.tsx";
import {useNavigate} from "react-router";
import {UserType} from "../../models/enum/user-type.enum.ts";
import {Warehouse} from "@mui/icons-material";

export function NavbarComponent(): React.JSX.Element {
  const { isAuthenticated, setIsAuthenticated, setUser, setAuthToken, user} = useAuthentication();
  const navigate = useNavigate();

  console.log(user);

  const handleSignOut = async () => {
    try {
      if (user?.userType === 'PRODUCER') {
        await signOutProducer()
      } else {
        await signOutClient();
      }
      setAuthToken(undefined);
      setUser(undefined);
      setIsAuthenticated(false);
      localStorage.removeItem('token');
      localStorage.removeItem('user');
    } catch (error) {
      console.error("Une erreur est survenue lors de la déconnexion :", error);
    }
  };

  const handleSignIn = async () => {
    navigate("/sign-in");
  };

    return (
        <nav className="flex items-center justify-between px-6 h-[70px] rounded-b-3xl bg-[#FFE4B9]" >

            {/* Gauche */}
            <div className="flex items-center gap-2">
                <div className="flex flex-col items-center ml-8 font-bold text-white" >
                  {isAuthenticated ?
                      <button onClick={handleSignOut}>
                        <LogoutIcon className="text-secondary text-2xl" />
                        <p className="text-secondary text-xs ">Déconnexion</p>
                      </button> :
                      <button onClick={handleSignIn}>
                        <LoginIcon className="text-secondary text-2xl" />
                        <p className="text-secondary text-xs ">Connexion</p>
                      </button>
                  }
                </div>
            </div>

            {/* Centre */}
            <div className="flex items-center justify-center">
                <a href="/home">
                    <img
                        src="src/assets/images/Logo.png"
                        alt="Logo"
                        className="w-12 h-12 object-contain"
                    />
                </a>
            </div>

            <div className="flex justify-center items-center gap-6">
              <div className="w-full flex flex-col items-center">
                <a href="/client-order-history">
                  <FormatListBulletedIcon className="text-[#FF5733] text-2xl" />
                </a>
                <span className="text-[#FF5733] text-xs font-bold">Historique</span>
              </div>
                {
                  user?.userType === UserType.CLIENT &&
                    <div className="w-full flex flex-col items-center">
                        <a href="/cart">
                            <ShoppingBagIcon className="text-[#FF5733] text-2xl" />
                        </a>
                        <span className="text-[#FF5733] text-xs font-bold">Historique</span>
                    </div>
                }
                {
                    user?.userType === UserType.PRODUCER &&
                    <div className="w-full flex flex-col items-center">
                        <a href="/producer/inventory">
                            <Warehouse className="text-[#FF5733] text-2xl" />
                        </a>
                        <span className="text-[#FF5733] text-xs font-bold">Inventaire</span>
                    </div>
                }
                <div>
                    <a href="/profile">
                        <AccountCircleIcon style={{ color: "#B02E0C", fontSize: "40px", marginRight: "30px" }} />
                    </a>
                </div>
            </div>

        </nav>

    );
}
