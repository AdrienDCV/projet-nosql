import React from "react";
import { JoinButtonComponent } from "../../components/button/join-button.component.tsx";
import { ProducerCard } from "../../components/cards/product-cards/producer-cards.component.tsx";
import {Carousel} from "../../components/carousel/carousel.tsx";

export function HomePage(): React.JSX.Element {

    return (
        <div className="w-full h-full flex flex-col">
          <Carousel />
          <ProducerCard />
          <JoinButtonComponent />
        </div>
    );
}
