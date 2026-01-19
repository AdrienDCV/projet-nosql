import type React from "react";
import { ProductCards } from "../../components/cards/product-cards/product-cards.component";

export const ProductsPage = (): React.JSX.Element => {
    return (
        <div className="w-full min-h-screen bg-[#FFF6E8] flex flex-col items-center">
            
            {/* Barre de recherche */}
            <div className="w-full flex justify-center py-8">
                <input
                    type="text"
                    placeholder="Viandes"
                    className="w-80 px-4 py-2 rounded-full shadow focus:outline-none"
                />
            </div>

            {/* Catégories */}
            <div className="bg-[#FFE3B3] rounded-3xl p-6 max-w-5xl w-full mb-12">
                <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
                    {[
                        "Légumes",
                        "Boucherie",
                        "Boulangerie",
                        "Patisserie",
                    ].map((category, index) => (
                        <button
                            key={index}
                            className="bg-white rounded-full px-4 py-2 shadow text-sm font-medium hover:bg-secondary hover:text-white transition"
                        >
                            {category}
                        </button>
                    ))}
                </div>
            </div>

            {/* Produits */}
            <ProductCards />
        </div>
    );
};
