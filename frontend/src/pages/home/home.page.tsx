import React from "react";
import { CarousselComponent } from "../../components/caroussel/caroussel.component.tsx";
import { JoinButtonComponent } from "../../components/button/join-button.component.tsx";
import { ProducerCard } from "../../components/cards/product-cards/product-cards.component.tsx";

export function HomePage(): React.JSX.Element {

    return (
        <div className="w-full h-full flex flex-col">
          <CarousselComponent />
          <ProducerCard />
          <JoinButtonComponent />
        </div>
    );
}
