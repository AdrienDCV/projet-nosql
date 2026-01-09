import React from "react";
import {Navbar} from "../../components/navbar/navbar.tsx";
import {Footer} from "../../components/footer/footer.tsx";
import {SearchBar} from "../../components/searchBar/searchBar.tsx";
import {BasketCard} from "../../components/cards/basket-card/basketCard.tsx";

export function Basket(): React.JSX.Element {

    return (
        <div className="flex flex-col min-h-screen bg-[#FFF6E8]">
            <Navbar/>
            <SearchBar/>
            <BasketCard/>

            <Footer/>
            <main>
            </main>
        </div>
    );
}
