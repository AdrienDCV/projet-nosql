import {Modal, TextField} from "@mui/material";
import Button from "@mui/material/Button";
import {useApp} from "../../hooks/app-context.hook.tsx";
import {useFormik} from "formik";
import type {Client} from "../../models/user.model.tsx";
import type {Address} from "../../models/address.model.tsx";
import type {ClientOrderItem, CreateClientOrderRequest} from "../../models/client-order.model.tsx";
import type {ClientCart} from "../../models/client-cart.model.tsx";

type IProps = {
  open: boolean;
  onClose: VoidFunction;
  currentClientCart: ClientCart;
  client: Client;
};

const validateForm = (values: {
  street: string,
  number: string,
  city: string,
  postalCode: string,
  country: string,
}) => {
  const errors: Record<string, string> = {};

  if (!values.street) {
    errors.street = 'La rue est requise';
  }

  if (!values.number) {
    errors.number = 'Le numéro de rue est requis';
  }

  if (!values.city) {
    errors.city = 'La ville est requise';
  }


  if (!values.postalCode) {
    errors.postalCode = 'Le code postal est requis';
  }

  if (!values.country) {
    errors.country = 'Le pays est requis';
  }

  return errors;
};

export type AddDeliveryAddressForm = {
  street: string,
  number: string,
  city: string,
  postalCode: string,
  country: string,
}

export const AddDeliveryAddressModal = ({open, onClose, currentClientCart, client}: IProps): React.JSX.Element => {
  const { createNewClientOrder } = useApp();

  const formik = useFormik<AddDeliveryAddressForm>({
    initialValues: {
      street: "",
      number: "",
      city: "",
      postalCode: "",
      country: "",
    },
    validate: validateForm,
    onSubmit: async(values) => {
      if (!client && !currentClientCart) return;

      const address: Address = {
          street: values.street,
          number: Number(values.number),
          city: values.city,
          postalCode: values.postalCode,
          country: values.country,
      }


      const clientOrderItems: ClientOrderItem[] = [];

      currentClientCart?.cartEntries.forEach(entry => {
        console.log(entry)
        clientOrderItems.push({
          productId: entry.productId,
          businessId: entry.businessId,
          label: entry.productName,
          unitPrice: entry.unitPrice,
          quantity: entry.quantity,
        })
      });

      const createClientOrderRequest: CreateClientOrderRequest = {
        clientId: client.clientId,
        orderItems: clientOrderItems,
        deliveryAddress: address,
        email: client.email,
        phone: client.phone,
      }

      void createNewClientOrder(createClientOrderRequest);

      formik.resetForm();

      onClose();
    }
  })

  return (
      <Modal open={open} onClose={onClose}>

        <div className=" absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 bg-[#EEEEEE] p-8 rounded-2xl outline-none w-[90vw] max-w-100">
          <div className="flex flex-col items-center gap-6">
            <h2 className="text-xl font-bold text-center">
              Addresse de livraison
            </h2>

            <form
                onSubmit={formik.handleSubmit}
                className="w-full h-full flex flex-col gap-5 justify-cente"
            >
              <div className="grid grid-cols-2 gap-4">
                <TextField
                    label="Nom de rue / avenue / boulevard"
                    name="street"
                    onChange={formik.handleChange}
                    value={formik.values.street}
                    error={formik.touched.street && !!formik.errors.street}
                    helperText={formik.touched.street && formik.errors.street}
                    required
                />
                <TextField
                    label="Numéro de voie"
                    name="number"
                    onChange={formik.handleChange}
                    value={formik.values.number}
                    error={formik.touched.number && !!formik.errors.number}
                    helperText={formik.touched.number && formik.errors.number}
                    required
                />
              </div>
              <div className="grid grid-cols-2 gap-4">
                <TextField
                    label="Ville"
                    name="city"
                    onChange={formik.handleChange}
                    value={formik.values.city}
                    error={formik.touched.city && !!formik.errors.city}
                    helperText={formik.touched.city && formik.errors.city}
                    required
                />
                <TextField
                    label="Code postal"
                    name="postalCode"
                    onChange={formik.handleChange}
                    value={formik.values.postalCode}
                    error={formik.touched.postalCode && !!formik.errors.postalCode}
                    helperText={formik.touched.postalCode && formik.errors.postalCode}
                    required
                />
              </div>
              <TextField
                  label="Pays"
                  name="country"
                  onChange={formik.handleChange}
                  value={formik.values.country}
                  error={formik.touched.country && !!formik.errors.country}
                  helperText={formik.touched.country && formik.errors.country}
                  required
              />

              <div className="flex flex-col justify-center gap-4">
                <Button
                    type="submit"
                    style={{
                      backgroundColor: '#FFE4B9',
                      borderRadius: '0.5rem',
                      color: 'black',
                      fontWeight: 'bold'
                    }}>
                  Valider et commander
                </Button>
                <Button
                    style={{
                      backgroundColor: '#B02E0C',
                      borderRadius: '0.5rem',
                      color: 'white',
                      fontWeight: 'bold'
                    }}
                    onClick={onClose}
                >
                  Annuler
                </Button>
              </div>
            </form>

          </div>
        </div>

      </Modal>
  );
};
