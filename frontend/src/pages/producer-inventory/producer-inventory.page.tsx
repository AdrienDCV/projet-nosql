import React, {useEffect} from "react";
import {useApp} from "../../hooks/app-context.hook.tsx";
import {ProductCard} from "../products/components/product-card.component.tsx";

export const ProducerInventoryPage = (): React.JSX.Element => {
  const { currentProducerInventory, currentProducerBusiness, refreshCurrentProducerInventory } = useApp();

  useEffect(() => {
    void refreshCurrentProducerInventory();
  }, [currentProducerBusiness]);

  const content = currentProducerInventory ?
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-8 max-w-6xl">
        {
          currentProducerInventory.map((product, index) => (
              <ProductCard
                  key={product.productId}
                  index={index}
                  image={product.image}
                  label={product.label}
                  unitPrice={product.price}
                  link= {`/product-update/${product.productId}`}
              />
          ))
        }
      </div> :
      <div>Inventaire vide...</div>

  return (
      <div className="w-full min-h-screen bg-[#FFF6E8] flex flex-col items-center">
        <div className="w-full flex flex-col justify-center items-center py-12">
          { content }
        </div>
      </div>
    );
};
