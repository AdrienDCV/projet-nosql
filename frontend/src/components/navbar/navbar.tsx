import React from "react";
import LogoutIcon from '@mui/icons-material/Logout';
import ShoppingBagIcon from '@mui/icons-material/ShoppingBag';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

export function Navbar(): React.JSX.Element {
    return (
        <nav className="flex items-center justify-between px-6 h-[70px] rounded-b-3xl" style={{ backgroundColor: '#FFE4B9' }}>

            {/* Gauche */}
            <div className="flex items-center gap-2">
                <div className="flex flex-col items-center font-bold text-white" style={{marginLeft: "30px" }}>
                    <LogoutIcon style={{ color: "#FF5733", fontSize: "32px" }} />
                    <p style={{ color: "#B02E0C" }}>Connexion</p>
                </div>
            </div>

            {/* Centre */}
            <div className="flex items-center justify-center">
                <img
                    src="src/assets/images/Logo.png"
                    alt="Logo"
                    className="w-12 h-12 object-contain"
                />
            </div>

            {/* Droite */}
            <div className="flex items-center gap-6">
                <div>
                    <ShoppingBagIcon style={{ color: "#FF5733", fontSize: "32px" }} />
                </div>
                <div>
                    <AccountCircleIcon style={{ color: "#B02E0C", fontSize: "40px", marginRight: "30px" }} />
                </div>
            </div>

        </nav>

    );
}
