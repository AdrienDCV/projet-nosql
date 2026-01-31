import InfoOutlineIcon from '@mui/icons-material/InfoOutline';
import {Link} from "react-router";

interface IProps {
  index: number;
  image: string;
  label: string;
  unitPrice: number
  link : string;
}

export const ProductCard = ({index, image, label, unitPrice, link}: IProps): React.JSX.Element => {

  return (
      <Link
          to={ link }
          key={ index }
          className="bg-white rounded-2xl shadow-md p-4 flex flex-col items-center text-center"
      >
        <div className="relative w-full">
          <img
              src={ image }
              alt={ `${label.toLowerCase().replaceAll(' ', '-')}-image` }
              className="w-full h-40 object-cover rounded-xl mb-4"
          />

          <span className="absolute top-2 left-2 bg-white rounded-full p-1 shadow">
            <InfoOutlineIcon />
          </span>
        </div>

        <h3 className="text-secondary font-semibold text-lg">
          { label }
        </h3>

        <p className="text-sm font-semibold">
          { unitPrice.toFixed(2) } €
        </p>
      </Link>
  )
}