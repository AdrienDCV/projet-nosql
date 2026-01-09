import React from "react";
import { Navbar } from "../../components/navbar/navbar.tsx";
import { Caroussel } from "../../components/caroussel/caroussel.tsx";
import {ProducerCard} from "../../components/cards/product-cards/producerCard.tsx";
import {Footer} from "../../components/footer/footer.tsx";
import {JoinButton} from "../../components/button/joinButton.tsx";
import {SearchBar} from "../../components/searchBar/searchBar.tsx";

export function Home(): React.JSX.Element {

    return (
        <div className="flex flex-col min-h-screen bg-[#FFF6E8]">
            <Navbar/>
            <SearchBar/>
            <Caroussel/>
            <ProducerCard />
            <JoinButton/>
            <Footer/>
            <main>
            </main>
        </div>
    );
}
