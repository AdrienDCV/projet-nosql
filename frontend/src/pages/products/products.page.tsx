import React, {useEffect} from "react";
import { ProductList } from "../../components/product-list/product-list.component.tsx";
import {useLocation} from "react-router";
import {useApp} from "../../hooks/app-context.hook.tsx";
import {SearchBar} from "../../components/searchBar/search.bar.tsx";

export const ProductsPage = (): React.JSX.Element => {
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const searchTerm = params.get("searchTerm") || "";
  const { refreshProductListProducer } = useApp();

  useEffect(() => {
    void refreshProductListProducer(searchTerm, 0);
  }, [searchTerm]);


    return (
        <div className="w-full min-h-screen bg-[#FFF6E8] flex flex-col items-center">
          <SearchBar />

            {
              /*
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
               */
            }

            <ProductList searchTerm={searchTerm} />
        </div>
    );
};
