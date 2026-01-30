import {useAuthentication} from "../../hooks/authentication-context.hook.tsx";
import {useFormik} from "formik";
import type {CreateProductRequest} from "../../models/create-product-request.model.tsx";
import {Button, MenuItem, Select, TextField} from "@mui/material";
import {useApp} from "../../hooks/app-context.hook.tsx";
import type {Producer} from "../../models/user.model.tsx";
import {UnitMesure} from "../../models/enum/unit-mesure.enum.tsx";


const validateForm = (values: {
    image: string,
    label: string,
    uniteMesure: UnitMesure,
    stock: number,
    price: number,
}) => {
    const errors: Record<string, string> = {};

    if (!values.image) {
        errors.name = 'Le liens de l\'image est requis';
    }

    if (!values.label) {
        errors.street = 'Le nom du produit est requise';
    }

    if (!values.uniteMesure) {
        errors.number = 'L\'unité de mesure est requis';
    }

    if (!values.stock) {
        errors.city = 'Le stock du produit est requise';
    }

    if (!values.price) {
        errors.postalCode = 'Le prix du produit est requis';
    }

    return errors;
};

export type CreateProductForm = {
    image: string,
    label: string,
    uniteMesure: UnitMesure,
    stock: number,
    price: number,
}

export const CreateProductsPage = (): React.JSX.Element => {
    const { user } = useAuthentication();
    const { createNewProduct } = useApp();

    const formik = useFormik<CreateProductForm>({
        initialValues: {
            image: "",
            label: "",
            uniteMesure: UnitMesure.UNIT,
            stock: 0,
            price: 0.0,
        },
        validate: validateForm,
        onSubmit: async(values) => {
            const producer: Producer = user as Producer;

            const createProductRequest: CreateProductRequest = {
                image: values.image,
                label: values.label,
                uniteMesure: values.uniteMesure,
                stock: values.stock,
                price: values.price,
            }

            createNewProduct(createProductRequest);

            formik.resetForm();
        }
    })

    return (
        <div className="w-full h-full flex flex-col items-center justify-center">
            <div className="w-[500px] p-11 bg-white rounded-lg shadow-md">
                <h2 className="text-[24px] font-bold mb-4 text-center">Créez votre Produit !</h2>
                <form
                    onSubmit={formik.handleSubmit}
                    className="w-full h-full flex flex-col gap-5 justify-cente"
                >
                    <TextField
                        label="Image"
                        name="Image"
                        onChange={formik.handleChange}
                        value={formik.values.image}
                        error={formik.touched.image && !!formik.errors.image}
                        helperText={formik.touched.image && formik.errors.image}
                        required
                    />
                    <TextField
                        label="Nom de votre produit"
                        name="name"
                        onChange={formik.handleChange}
                        value={formik.values.label}
                        error={formik.touched.label && !!formik.errors.label}
                        helperText={formik.touched.label && formik.errors.label}
                        required
                    />
                    <TextField
                        label="Stock"
                        name="stock"
                        onChange={formik.handleChange}
                        value={formik.values.stock}
                        error={formik.touched.stock && !!formik.errors.stock}
                        helperText={formik.touched.stock && formik.errors.stock}
                        required
                    />
                    <Select
                        label="Unité de mesure"
                        name="unite Mesure"
                        value={formik.values.uniteMesure}
                        onChange={formik.handleChange}
                        error={formik.touched.uniteMesure && !!formik.errors.uniteMesure}
                        required
                    >
                        <MenuItem value={UnitMesure.UNIT}>Unité</MenuItem>
                        <MenuItem value={UnitMesure.G}>Gramme</MenuItem>
                        <MenuItem value={UnitMesure.KG}>Kilogramme</MenuItem>
                        <MenuItem value={UnitMesure.ML}>MiliLitre</MenuItem>
                        <MenuItem value={UnitMesure.L}>Litre</MenuItem>
                    </Select>
                    <TextField
                        label="Prix"
                        name="price"
                        onChange={formik.handleChange}
                        value={formik.values.price}
                        error={formik.touched.price && !!formik.errors.price}
                        helperText={formik.touched.price && formik.errors.price}
                        required
                    />
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