import {useApp} from "../../../hooks/app-context.hook.tsx";
import {ProductCard} from "../../../pages/products/components/product-card.component.tsx";

export function ProductList() {
    const { productList } = useApp();

    return (
        <section className="w-full flex justify-center py-12">
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-8 max-w-6xl">
                {productList?.content.map(
                    (product, index) => (
                        <ProductCard
                            index={index}
                            productId={product.productId}
                            image={product.image}
                            label={product.label}
                            unitPrice={product.price}
                        />
                    )
                )}
            </div>
        </section>
    );
}
