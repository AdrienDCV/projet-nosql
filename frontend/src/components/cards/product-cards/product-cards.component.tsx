import {useApp} from "../../../hooks/app-context.hook.tsx";

type Product = {
    name: string;
    image: string;
    priceKg: string;
    priceUnit: string;
};

const products: Product[] = [
    {
        name: "Filet mignon",
        image: "https://www.calories.fr/images/fr/640/porc-filet-mignon.jpg",
        priceKg: "98€/kg",
        priceUnit: "49€ - 500g",
    },
    {
        name: "Entrecote",
        image: "https://www.krill.fr/media/catalog/product/cache/7db168f77a3167534cae1e7a02390ea5/0/1/013012-Entrecote-de-boeuf-150-170-g-00573359.jpg",
        priceKg: "24€/kg",
        priceUnit: "6€ - 250g",
    },
    {
        name: "Filet mignon",
        image: "https://www.calories.fr/images/fr/640/porc-filet-mignon.jpg",
        priceKg: "98€/kg",
        priceUnit: "49€ - 500g",
    },
    {
        name: "Entrecote",
        image: "https://www.krill.fr/media/catalog/product/cache/7db168f77a3167534cae1e7a02390ea5/0/1/013012-Entrecote-de-boeuf-150-170-g-00573359.jpg",
        priceKg: "24€/kg",
        priceUnit: "6€ - 250g",
    },
];

export function ProductList() {
    const { getProductDetails } = useApp();

    return (
        <section className="w-full flex justify-center py-12">
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-8 max-w-6xl">
                {products.map((product, index) => (
                    <div
                        onClick={() => getProductDetails(product.productId)}
                        key={index}
                        className="bg-white rounded-2xl shadow-md p-4 flex flex-col items-center text-center"
                    >
                        <div className="relative w-full">
                            <img
                                src={product.image}
                                alt={product.name}
                                className="w-full h-40 object-cover rounded-xl mb-4"
                            />

                            {/* Icône info */}
                            <span className="absolute top-2 left-2 bg-white rounded-full p-1 shadow">
                                ℹ️
                            </span>
                        </div>

                        <h3 className="text-secondary font-semibold text-lg">
                            {product.name}
                        </h3>

                        <p className="text-sm text-gray-700 mt-2">
                            {product.priceKg}
                        </p>

                        <p className="text-sm font-semibold">
                            {product.priceUnit}
                        </p>
                    </div>
                ))}
            </div>
        </section>
    );
}
