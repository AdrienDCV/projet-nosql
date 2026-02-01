import {useFormik} from "formik";
import type {CreateProductRequest} from "../../models/create-product-request.model.tsx";
import {Button, MenuItem, Select, TextField} from "@mui/material";
import {useApp} from "../../hooks/app-context.hook.tsx";
import {MeasurementUnit} from "../../models/enum/measurement-unit.enum.ts";
import {useNavigate} from "react-router-dom";

const validateForm = (values: {
    label: string,
    image: string,
    measurementUnit: MeasurementUnit,
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

    if (!values.measurementUnit) {
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
    label: string,
    image: string,
    description: string,
    measurementUnit: MeasurementUnit,
    stock: number,
    price: number,
}

export const CreateProductsPage = (): React.JSX.Element => {
    const { createNewProduct, refreshCurrentProducerInventory, currentProducerBusiness } = useApp();
    const navigate = useNavigate();

    const formik = useFormik<CreateProductForm>({
        initialValues: {
            label: "",
            image: "",
            description: "",
            measurementUnit: MeasurementUnit.UNIT,
            stock: 0,
            price: 0.0,
        },
        validate: validateForm,
        onSubmit: async(values) => {
            if (!currentProducerBusiness) return;

            const createProductRequest: CreateProductRequest = {
                label: values.label,
                image: values.image,
                description: values.description,
                measurementUnit: values.measurementUnit,
                stock: values.stock,
                price: Number(values.price.toString().replaceAll(',', '.')),
                businessName: currentProducerBusiness?.name,
                businessId: currentProducerBusiness?.businessId
            }

            createNewProduct(createProductRequest);
            refreshCurrentProducerInventory();

            formik.resetForm();

            navigate("/producer/inventory")
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
                        name="image"
                        onChange={formik.handleChange}
                        value={formik.values.image}
                        error={formik.touched.image && !!formik.errors.image}
                        helperText={formik.touched.image && formik.errors.image}
                        required
                    />
                    <TextField
                        label="Nom de votre produit"
                        name="label"
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
                        name="measurementUnit"
                        value={formik.values.measurementUnit}
                        onChange={formik.handleChange}
                        error={formik.touched.measurementUnit && !!formik.errors.measurementUnit}
                        required
                    >
                        <MenuItem value={MeasurementUnit.UNIT}>Unité</MenuItem>
                        <MenuItem value={MeasurementUnit.G}>Gramme</MenuItem>
                        <MenuItem value={MeasurementUnit.KG}>Kilogramme</MenuItem>
                        <MenuItem value={MeasurementUnit.ML}>MiliLitre</MenuItem>
                        <MenuItem value={MeasurementUnit.L}>Litre</MenuItem>
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