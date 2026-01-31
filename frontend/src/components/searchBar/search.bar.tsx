import { useState } from "react";
import { FiSearch, FiX } from "react-icons/fi";
import {useNavigate} from "react-router";

export function SearchBar() {
    const [searchTerm, setSearchTerm] = useState("");
    const navigate = useNavigate();

    const handleSearch = () => {
        navigate(`/products?searchTerm=${encodeURIComponent(searchTerm)}`);
        setSearchTerm("");
    }

    const handleClear = () => {
        setSearchTerm("");
    }

    return (
        <div className="w-128 max-w-4xl mx-auto py-16 px-4">
            <div className="relative flex bg-white rounded-full items-center">
                <input
                    type="text"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    placeholder="Rechercher un produit..."
                    className="w-full p-3 pl-10 pr-10 rounded-full border border-gray-300 focus:outline-none focus:ring-2 focus:ring-orange-500"
                />

                {searchTerm && (
                    <button
                        onClick={handleClear}
                        className="absolute left-3 text-gray-400 hover:text-gray-700"
                    >
                        <FiX size={20} />
                    </button>
                )}

                <button onClick={handleSearch} className="absolute right-3 text-gray-500 hover:text-gray-700">
                    <FiSearch size={20} />
                </button>
            </div>
        </div>
    );
}
