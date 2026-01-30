import React from "react";
import CropOriginalIcon from "@mui/icons-material/CropOriginal";
import { useRef, useState } from "react";

export function ProfilePage(): React.JSX.Element {

    const fileInputRef = useRef<HTMLInputElement>(null);
    const [preview, setPreview] = useState<string | null>(null);

    const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];
        if (file) {
            setPreview(URL.createObjectURL(file));
        }
    };

    // Valeurs par défaut
    const defaultProfile = {
        email: "jean.dupont@email.com",
        nom: "Dupont",
        prenom: "Jean",
        password: "Motdepasse123",
        role: "consumer",
    };

    return (
        <div className="flex flex-col w-full h-full bg-[#FFF6E8]">

            <div className="w-full h-full bg-[#FFF6E8] flex justify-center items-center px-6">
                <div className="w-full max-w-lg bg-white rounded-2xl shadow-lg p-8 space-y-6">

                    {/* Photo de profil */}
                    <div className="flex justify-center">
                        <div
                            onClick={() => fileInputRef.current?.click()}
                            className="relative cursor-pointer group w-32 h-32 rounded-full shadow-lg border-4 border-white bg-gray-100 flex items-center justify-center"
                        >
                            {preview ? (
                                <img
                                    src={preview}
                                    alt="Photo de profil"
                                    className="w-full h-full rounded-full object-cover"
                                />
                            ) : (
                                <CropOriginalIcon sx={{ fontSize: 50, color: "#9CA3AF" }} />
                            )}

                            {/* Overlay */}
                            <div className="absolute inset-0 bg-black/40 rounded-full opacity-0 group-hover:opacity-100 flex items-center justify-center transition">
                              <span className="text-white text-sm font-semibold">
                                Modifier
                              </span>
                            </div>
                        </div>
                        <input
                            ref={fileInputRef}
                            type="file"
                            accept="image/*"
                            readOnly
                            className="hidden"
                            onChange={handleImageChange}
                        />
                    </div>

                    {/* Titre */}
                    <h2 className="text-2xl font-bold text-center text-[#EB4511]">
                        Mon Profil
                    </h2>

                    {/* Email */}
                    <div>
                        <label className="block text-sm font-medium mb-1">
                            Adresse Email
                        </label>
                        <input
                            type="email"
                            value={defaultProfile.email}
                            readOnly
                            className="w-full border rounded-lg px-4 py-2 focus:ring-2 focus:ring-orange-400"
                        />
                    </div>

                    {/* Nom / Prénom */}
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                        <div>
                            <label className="block text-sm font-medium mb-1">Nom</label>
                            <input
                                type="text"
                                readOnly
                                value={defaultProfile.nom}
                                className="w-full border rounded-lg px-4 py-2 focus:ring-2 focus:ring-orange-400"
                            />
                        </div>
                        <div>
                            <label className="block text-sm font-medium mb-1">Prénom</label>
                            <input
                                type="text"
                                readOnly
                                value={defaultProfile.prenom}
                                className="w-full border rounded-lg px-4 py-2 focus:ring-2 focus:ring-orange-400"
                            />
                        </div>
                    </div>

                    {/* Mot de passe */}
                    <div>
                        <label className="block text-sm font-medium mb-1">Mot de passe</label>
                        <input
                            type="password"
                            readOnly
                            value={defaultProfile.password}
                            className="w-full border rounded-lg px-4 py-2 focus:ring-2 focus:ring-orange-400"
                        />
                    </div>

                    {/* Type */}
                    <div>
                        <label className="block text-sm font-medium mb-1">Type de profil</label>
                        <div className="flex items-center gap-2">
                            {defaultProfile.role === "consumer" && (
                                <span className="px-3 py-1 bg-orange-100 text-orange-800 rounded-full">
                                    Consommateur
                                </span>
                            )}
                            {defaultProfile.role === "producer" && (
                                <span className="px-3 py-1 bg-orange-100 text-orange-800 rounded-full">
                                    Producteur
                                </span>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}