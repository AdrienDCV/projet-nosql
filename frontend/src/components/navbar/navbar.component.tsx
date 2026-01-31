import React from "react";
import LoginIcon from '@mui/icons-material/Login';
import LogoutIcon from '@mui/icons-material/Logout';
import ShoppingBagIcon from '@mui/icons-material/ShoppingBag';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import FormatListBulletedIcon from '@mui/icons-material/FormatListBulleted';
import {useAuthentication} from "../../hooks/authentication-context.hook.tsx";
import {signOutClient, signOutProducer} from "../../services/user.service.tsx";
import {useNavigate} from "react-router";

export function NavbarComponent(): React.JSX.Element {
  const { isAuthenticated, setIsAuthenticated, setUser, setAuthToken, user} = useAuthentication();
  const navigate = useNavigate();

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
                        <LogoutIcon style={{ color: "#FF5733", fontSize: "32px" }} />
                        <p className="text-secondary">Déconnexion</p>
                      </button> :
                      <button onClick={handleSignIn}>
                        <LoginIcon style={{ color: "#FF5733", fontSize: "32px" }} />
                        <p className="text-secondary">Connexion</p>
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

            {/* Droite */}
            <div className="flex items-center gap-6">
                <div>
                    <a href="/order-list">
                        <FormatListBulletedIcon style={{ color: "#ff6a41", fontSize: "32px" }} />
                    </a>
                </div>
                <div>
                    <a href="/cart">
                        <ShoppingBagIcon style={{ color: "#FF5733", fontSize: "32px" }} />
                    </a>
                </div>
                <div>
                    <a href="/profile">
                        <AccountCircleIcon style={{ color: "#B02E0C", fontSize: "40px", marginRight: "30px" }} />
                    </a>
                </div>
            </div>

        </nav>

    );
}
