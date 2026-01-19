import React, {useState} from "react";
import {useFormik} from "formik";
import type {UserSignUpRequest} from "../../../models/user.model.tsx";
import {TextField, Button, Checkbox, FormControlLabel} from "@mui/material";
import {NavLink} from "react-router";
import {useAuthentication} from "../../../hooks/authentication-context.hook.tsx";

const validateForm = (values: {
  firstname: string;
  lastname: string;
  email: string;
  phone: string;
  password: string;
  confirmPassword: string;
}) => {
  const errors: Record<string, string> = {};

  if (!values.firstname) {
    errors.firstname = 'Le prénom est requis';
  }
  if (!values.lastname) {
    errors.lastname = 'Le nom est requis';
  }
  if (!values.email) {
    errors.email = 'L\'email est requis';
  }
  if (!values.phone) {
    errors.phone = 'Le téléphone est requis';
  }
  if (!values.password) {
    errors.password = 'Le mot de passe est requis';
  }
  if (values.password.length < 8) {
    errors.password = 'Le mot de passe doit contenir au moins 8 caractères';
  }
  if (values.confirmPassword !== values.password) {
    errors.confirmPassword = 'Les mots de passe ne correspondent pas';
  }

  return errors;
};

export const SignUpPage = (): React.JSX.Element => {
  const [isProducer, setIsProducer] = useState<boolean>(false);
  const { registerClient, registerProducer } = useAuthentication();

  const handleRoleToggle = () => {
    setIsProducer(!isProducer);
  }

  const formik = useFormik({
    initialValues: {
      firstname: "",
      lastname: "",
      email: "",
      phone: "",
      password: "",
      confirmPassword: "",
    },
    validate: validateForm,
    onSubmit: async(values) => {
      const userData: UserSignUpRequest = {
        username: values.email,
        firstname: values.firstname,
        lastname: values.lastname,
        email: values.email,
        phone: values.phone,
        password: values.password,
      }

      if (isProducer) {
        registerProducer(userData);
      } else {
        registerClient(userData);
      }

      formik.resetForm();
    }
  })

  return (
      <div className="w-full h-full flex flex-col items-center justify-center">
        <div className="w-[500px] p-11 bg-white rounded-lg shadow-md">
          <h2 className="text-[24px] font-bold mb-4 text-center">Inscription</h2>
          <form
              onSubmit={formik.handleSubmit}
              className="w-full h-full flex flex-col gap-5 justify-cente"
          >
            <FormControlLabel
                control={
                  <Checkbox
                      checked={isProducer}
                      onChange={handleRoleToggle}
                  />
                }
                label="Je suis un Producteur"
            />

            <TextField
                label="Nom"
                name="lastname"
                onChange={formik.handleChange}
                value={formik.values.lastname}
                error={formik.touched.lastname && !!formik.errors.lastname}
                helperText={formik.touched.lastname && formik.errors.lastname}
                required
            />
            <TextField
                label="Prénom"
                name="firstname"
                onChange={formik.handleChange}
                value={formik.values.firstname}
                error={formik.touched.firstname && !!formik.errors.firstname}
                helperText={formik.touched.firstname && formik.errors.firstname}
                required
            />
            <TextField
                type="email"
                label="Email"
                name="email"
                onChange={formik.handleChange}
                value={formik.values.email}
                error={formik.touched.email && !!formik.errors.email}
                helperText={formik.touched.email && formik.errors.email}
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
                label="Mot de passe"
                name="password"
                type="password"
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.password}
                error={formik.touched.password && !!formik.errors.password}
                helperText={formik.touched.password && formik.errors.password}
                required
            />
            <TextField
                label="Confirmer le mot de passe"
                name="confirmPassword"
                type="password"
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.confirmPassword}
                error={formik.touched.confirmPassword && !!formik.errors.confirmPassword}
                helperText={formik.touched.confirmPassword && formik.errors.confirmPassword}
                required
            />

            {
              /*
              isProducer &&
                <>
                  <Select
                      label="Domaine métier"
                      name="profession"
                      value={formik.values.profession}
                      onChange={formik.handleChange}
                      required
                  >
                      <MenuItem value={Profession.DIVERSE}>Divers</MenuItem>
                      <MenuItem value={Profession.BUTCHERY}>Boucherie</MenuItem>
                      <MenuItem value={Profession.BAKERY}>Boulangerie</MenuItem>
                      <MenuItem value={Profession.MARKET_GARDENER}>Boulangerie</MenuItem>
                  </Select>
                </>
                */
            }
            <Button
                type='submit'
                style={{
                  backgroundColor: '#B02E0C',
                  borderRadius: '0.5rem',
                  color: 'white',
                }}
            >
              <span>S'inscrire</span>
            </Button>
            <div className="flex justify-center gap-2">
              <p className="m-0"><b>Vous avez déjà un compte ?</b></p>
              <NavLink
                  to="/sign-in"
                  style={{color: 'blue', textDecoration: 'none'}}
              >
                Se connecter
              </NavLink>
            </div>
          </form>
        </div>
      </div>
  )
}