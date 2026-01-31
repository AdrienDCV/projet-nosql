import React, { useEffect, useRef } from "react";
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import { Link, useParams } from "react-router-dom";
import { useApp } from "../../hooks/app-context.hook.tsx";

export const ProductDetailsPage = (): React.JSX.Element => {
    const { productId } = useParams<{ productId: string }>();
    const { getProductDetails, currentProductDetails } = useApp();

    const lastFetchedIdRef = useRef<string | null>(null);

    useEffect(() => {
        if (!productId) return;
        if (lastFetchedIdRef.current === productId) return;

        lastFetchedIdRef.current = productId;
        void getProductDetails(productId);
    }, [productId, getProductDetails]);

    return (
        <div className="w-full min-h-screen bg-[#FFF6E8] flex flex-col items-center py-10">
            <div className="w-full flex items-center max-w-4xl mb-4">
                <Link to="/products" className="text-sm text-gray-700">
                    <ChevronLeftIcon />
                    Retour
                </Link>
            </div>

            <div className="bg-white rounded-3xl shadow-lg p-8 max-w-4xl w-full flex flex-col md:flex-row gap-8">
                <div className="shrink-0">
                    <img
                        src={currentProductDetails?.image}
                        alt="product-image"
                        className="w-72 h-72 object-cover rounded-2xl"
                    />
                </div>

                <div className="flex flex-col justify-center gap-4">
                    <h1 className="text-2xl font-semibold text-secondary">
                        {currentProductDetails?.label}
                    </h1>

                    <div className="bg-gray-100 rounded-full px-4 py-2 text-sm w-fit">
                        {currentProductDetails?.price.toFixed(2)} €
                    </div>

                    {currentProductDetails?.description && (
                        <p className="text-sm text-gray-700 max-w-md">
                            {currentProductDetails?.description}
                        </p>
                    )}

                    <button className="bg-secondary text-white rounded-full px-6 py-2 w-fit hover:opacity-90 transition">
                        {currentProductDetails?.businessName}
                    </button>
                </div>
            </div>
        </div>
    );
};
