import { useApp } from "../../../hooks/app-context.hook.tsx";
import { ProductCard } from "../../../pages/products/components/product-card.component.tsx";
import KeyboardArrowLeftIcon from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRightIcon from '@mui/icons-material/KeyboardArrowRight';

interface IProps {
  searchTerm: string
}

export function ProductList({searchTerm}: IProps) {
  const { productList, refreshProductListProducer } = useApp();

  if (!productList) {
    return <div>No content..</div>;
  }

  const handlePreviousPageClick = ()=>  {
    if (productList.pageNumber - 1 < 0) return;
    void refreshProductListProducer(searchTerm, productList.pageNumber - 1);
  }

  const handleNextPageClick = ()=>  {
    if (productList.pageNumber + 1 >= productList.totalPages) return;
    void refreshProductListProducer(searchTerm, productList.pageNumber + 1);
  }

  return (
      <div className="w-full flex flex-col justify-center items-center py-12">
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-8 max-w-6xl">
          {productList.content.map((product, index) => (
              <ProductCard
                  key={product.productId}
                  index={index}
                  productId={product.productId}
                  image={product.image}
                  label={product.label}
                  unitPrice={product.price}
              />
          ))}
        </div>

        <div className="flex flex-row justify-between items-center mt-22 gap-8">
          <button onClick={handlePreviousPageClick}>
            <KeyboardArrowLeftIcon className="" />
          </button>
          Page {productList.pageNumber + 1} sur {productList.totalPages}
          <button onClick={handleNextPageClick}>
            <KeyboardArrowRightIcon className=""/>
          </button>
        </div>
      </div>
  );
}
