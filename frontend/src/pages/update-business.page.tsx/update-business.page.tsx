import {useAuthentication} from "../../hooks/authentication-context.hook.tsx";
import {useFormik} from "formik";
import {Button, MenuItem, Select, TextField} from "@mui/material";
import {Profession} from "../../models/enum/profession.enum.ts";
import {useApp} from "../../hooks/app-context.hook.tsx";
import type {Producer} from "../../models/user.model.tsx";
import type { UpdateBusinessRequest } from "../../models/update-business-request.model.tsx";


const validateForm = (values: {
  name: string,
  street: string,
  number: string,
  city: string,
  postalCode: string,
  country: string,
  profession: Profession,
  description: string,
  phone: string,
  email: string,
}) => {
  const errors: Record<string, string> = {};

  if (!values.name) {
    errors.name = 'Le nom du commerce est requis';
  }

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

  if (!values.profession) {
    errors.profession = 'Le domaine métier du commerce est requis';
  }

  if (!values.phone) {
    errors.phone = 'L\'adresse du commerce est requise';
  }

  if (!values.email) {
    errors.email = 'L\'adresse email est requise';
  }

  return errors;
};

export type UpdateBusinessForm = {
  name: string,
  street: string,
  number: string,
  city: string,
  postalCode: string,
  country: string,
  profession: Profession,
  description: string,
  phone: string,
  email: string,
}

export const UpdateBusinessPage = (): React.JSX.Element => {
  const { user } = useAuthentication();
  const { updateBusiness, currentProducerBusiness } = useApp();

  const formik = useFormik<UpdateBusinessForm>({
    enableReinitialize: true,
    initialValues: {
      name: currentProducerBusiness?.name ?? "",
      street: currentProducerBusiness?.address.street ?? "",
      number: currentProducerBusiness?.address.number.toString() ?? "",
      city: currentProducerBusiness?.address.city ?? "",
      postalCode: currentProducerBusiness?.address.postalCode ?? "",
      country: currentProducerBusiness?.address.country ?? "",
      profession: currentProducerBusiness?.profession ?? Profession.DIVERSE,
      description: currentProducerBusiness?.description ?? "",
      phone: currentProducerBusiness?.phone ?? "",
      email: currentProducerBusiness?.email ?? "",
    },
    validate: validateForm,
    onSubmit: async(values) => {
      if (!currentProducerBusiness) return;

      const producer: Producer = user as Producer;

      const updateBusinessRequest: UpdateBusinessRequest = {
        name: values.name,
        address: {
          street: values.street,
          number: Number(values.number),
          city: values.city,
          postalCode: values.postalCode,
          country: values.country,
        },
        profession: values.profession,
        description: values.description,
        phone: values.phone,
        email: values.email,
        producerId: producer.producerId,
        businessId: currentProducerBusiness?.businessId
      }

      updateBusiness(updateBusinessRequest);

      formik.resetForm();
    }
  })

  return (
      <div className="w-full h-full flex flex-col items-center justify-center">
        <div className="w-[500px] p-11 bg-white rounded-lg shadow-md">
          <h2 className="text-[24px] font-bold mb-4 text-center">Modifiez votre commerce</h2>
          <form
              onSubmit={formik.handleSubmit}
              className="w-full h-full flex flex-col gap-5 justify-cente"
          >
            <TextField
                label="Nom de votre commerce"
                name="name"
                onChange={formik.handleChange}
                value={formik.values.name}
                error={formik.touched.name && !!formik.errors.name}
                helperText={formik.touched.name && formik.errors.name}
                required
            />
            <TextField
                label="Téléphone"
                name="phone"
                onChange={formik.handleChange}
                value={formik.values.phone}
                error={formik.touched.phone && !!formik.errors.phone}
                helperText={formik.touched.phone && formik.errors.phone}
                required
            />
            <TextField
                label="Adresse email"
                name="email"
                onChange={formik.handleChange}
                value={formik.values.email}
                error={formik.touched.email && !!formik.errors.email}
                helperText={formik.touched.email && formik.errors.email}
                required
            />
            <Select
                label="Domaine métier"
                name="profession"
                value={formik.values.profession}
                onChange={formik.handleChange}
                error={formik.touched.profession && !!formik.errors.profession}
                required
            >
              <MenuItem value={Profession.DIVERSE}>Divers</MenuItem>
              <MenuItem value={Profession.BUTCHERY}>Boucherie</MenuItem>
              <MenuItem value={Profession.BAKERY}>Boulangerie</MenuItem>
              <MenuItem value={Profession.MARKET_GARDENER}>Boulangerie</MenuItem>
            </Select>
            <>
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
            </>

            <Button
                type='submit'
                style={{
                  backgroundColor: '#B02E0C',
                  borderRadius: '0.5rem',
                  color: 'white',
                }}
            >
              <span>Confirmer</span>
            </Button>
          </form>
        </div>
      </div>
  );
}