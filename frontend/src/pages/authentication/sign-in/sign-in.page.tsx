import React, {useEffect, useState} from "react";
import {useFormik} from "formik";
import {Checkbox, FormControlLabel, TextField} from "@mui/material";
import Button from "@mui/material/Button";
import {NavLink} from "react-router";
import {useAuthentication} from "../../../hooks/authentication-context.hook.tsx";

export type SignInForm = {
  username: string,
  password: string,
}

export const SignInPage = (): React.JSX.Element => {
  const [isProducer, setIsProducer] = useState<boolean>(false);
  const { logClientIn, logProducerIn } = useAuthentication();

  useEffect(() => {
    // eslint-disable-next-line react-hooks/immutability
    formik.resetForm();
  }, []);

  const handleRoleToggle = () => {
    setIsProducer(!isProducer);
  }

  const formik = useFormik<SignInForm>({
    initialValues: {
      username: '',
      password: '',
    },
    onSubmit: async (values) => {
      const username = values.username;
      const password = values.password;

      if (isProducer) {
        logProducerIn(username, password);
      } else {
        logClientIn(username, password);
      }

      formik.resetForm();
    },
  });

  return (
      <div className="w-full h-full flex flex-col items-center justify-center">
        <div className="w-[500px] p-11 bg-white rounded-lg shadow-md">
          <h2 className="text-[24px] font-bold mb-4 text-center">Connexion</h2>
          <form
              onSubmit={formik.handleSubmit}
              className='w-full h-full flex flex-col gap-5 justify-center'
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

              <TextField type='text'
                         label='Identifiant'
                         onChange={formik.handleChange}
                         name='username'
                         value={formik.values.username}
                         required
              />

              <TextField type='password'
                         label='Mot de passe'
                         name='password'
                         value={formik.values.password}
                         required
                         onChange={formik.handleChange}
              />

              <Button
                  type='submit'
                  style={{
                    backgroundColor: '#B02E0C',
                    borderRadius: '0.5rem',
                    color: 'white',
                  }}
              >
                <span>Se connecter</span>
              </Button>
              <div className="flex justify-center gap-2">
                <p className="m-0"><b>Pas encore inscrit ?</b></p>
                <NavLink
                    to="/sign-up"
                    style={{color: 'blue', textDecoration: 'none'}}
                >
                  S'inscrire
                </NavLink>
              </div>
          </form>
        </div>
      </div>
  )
}