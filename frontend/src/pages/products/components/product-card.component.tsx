import InfoOutlineIcon from '@mui/icons-material/InfoOutline';
import {useNavigate} from "react-router-dom";
import {useAuthentication} from "../../../hooks/authentication-context.hook.tsx";
import {UserType} from "../../../models/enum/user-type.enum.ts";

interface IProps {
  index: number;
  image: string;
  label: string;
  unitPrice: number;
  link: string;
}

export const ProductCard = ({index, image, label, unitPrice, link}: IProps): React.JSX.Element => {
  const { user } = useAuthentication();
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(link);
  }

  return (
      <div
          key={index}
          className="bg-white rounded-2xl shadow-md p-4 flex flex-col items-center text-center relative
                 w-full h-65"
          onClick={() => {
            if (user?.userType === UserType.PRODUCER) handleClick();
          }}
      >
        <div className="relative w-full h-40">
          <img
              src={image}
              alt={`${label.toLowerCase().replaceAll(' ', '-')}-image`}
              className="w-full h-full object-cover rounded-xl mb-4"
          />

          {user?.userType === UserType.CLIENT && (
              <div
                  className="absolute top-2 left-2"
                  onClick={() => {
                    handleClick();
                  }}
              >
                <InfoOutlineIcon className="text-white" />
              </div>
          )}
        </div>

        {/* Bloc texte fixé */}
        <div className="flex flex-col items-center justify-between flex-1 w-full">
          <h3 className="text-secondary font-semibold text-lg line-clamp-2">
            {label}
          </h3>

          <p className="text-sm font-semibold">
            {unitPrice.toFixed(2)} €
          </p>
        </div>
      </div>
  );
}
