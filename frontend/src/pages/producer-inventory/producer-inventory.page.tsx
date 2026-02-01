import React, {useEffect} from "react";
import {useApp} from "../../hooks/app-context.hook.tsx";
import {ProductCard} from "../products/components/product-card.component.tsx";
import Button from "@mui/material/Button";
import {useNavigate} from "react-router-dom";

export const ProducerInventoryPage = (): React.JSX.Element => {
  const { currentProducerInventory, currentProducerBusiness, refreshCurrentProducerInventory } = useApp();
  const navigate = useNavigate();

  useEffect(() => {
    void refreshCurrentProducerInventory();
  }, [currentProducerBusiness]);

  const content = currentProducerInventory ? (
      <div className="w-full max-w-6xl mx-auto flex flex-col gap-10">

        {/* Bouton à gauche */}
        <div className="w-full flex justify-start mt-4">
          <Button
              onClick={() => navigate("/create-product")}
              style={{
                backgroundColor: '#B02E0C',
                borderRadius: '0.50rem',
                color: 'white',
                fontWeight: 'bold',
                padding: '8px 16px',
              }}
          >
            Créer un nouveau produit
          </Button>
        </div>

        {/* Grid des cards */}
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-8 w-full">
          {currentProducerInventory.map((product, index) => (
              <ProductCard
                  key={product.productId}
                  index={index}
                  image={product.image}
                  label={product.label}
                  unitPrice={product.price}
                  link={`/product-update/${product.productId}`}
              />
          ))}
        </div>
      </div>
  ) : (
      <div className="text-gray-600 font-semibold">Inventaire vide...</div>
  );

  return (
      <div className="w-full min-h-screen bg-[#FFF6E8] flex flex-col items-center">
        <div className="w-full flex flex-col justify-center items-center py-12">
          {content}
        </div>
      </div>
  );
};
