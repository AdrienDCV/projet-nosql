import React, {useEffect, useRef, useState} from "react";
import {Link, useParams} from "react-router-dom";
import {useApp} from "../../hooks/app-context.hook.tsx";
import {Toast} from "../../components/toast/toats.component.tsx";
import {useFormik} from "formik";
import type {UpdateProductRequest} from "../../models/update-product-resquest.model.tsx";
import {Button, MenuItem, Select, TextField} from "@mui/material";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import {StockStatus} from "../../models/enum/stock-status.enum.ts";

const validateForm = (values: {
    label: string,
    image: string,
    stock: number,
    stockStatus: StockStatus,
    price: number,
}) => {
    const errors: Record<string, string> = {};

    if (!values.image) {
        errors.image = 'Le liens de l\'image est requis';
    }

    if (!values.label) {
        errors.label = 'Le nom du produit est requise';
    }

    if (!values.stock) {
        errors.stock = 'Le stock du produit est requise';
    }

    if (!values.price) {
        errors.price = 'Le prix du produit est requis';
    }

    if (!values.stockStatus) {
        errors.stockStatus = 'Le statut du stock est requis';
    }

    return errors;
};

type UpdateProductForm = {
    label: string,
    image: string,
    description: string,
    stock: number,
    price: number,
    stockStatus: StockStatus,
};

export const ProductUpdatesPage = (): React.JSX.Element => {
    const { productId } = useParams<{ productId: string }>();
    const { currentProductDetails, currentProducerBusiness, getProductDetails, updateCurrentProduct } = useApp();

    const lastFetchedIdRef = useRef<string | null>(null);

    const [toast, setToast] = useState<{
        message: string;
        type: "success" | "error";
    } | null>(null);

    useEffect(() => {
        if (!productId) return;
        if (lastFetchedIdRef.current === productId) return;

        lastFetchedIdRef.current = productId;
        void getProductDetails(productId);
    }, [productId, getProductDetails]);

    const formik = useFormik<UpdateProductForm>({
        enableReinitialize: true,
        initialValues: {
            label: currentProductDetails?.label ?? "",
            image: currentProductDetails?.image ?? "",
            description: currentProductDetails?.description ?? "",
            stock: currentProductDetails?.stock ?? 0,
            price: currentProductDetails?.price ?? 0.0,
            stockStatus: currentProductDetails?.stockStatus ?? StockStatus.LIMITED_STOCK,
        },
        validate: validateForm,
        onSubmit: async(values) => {
            if (!currentProducerBusiness || !currentProductDetails) return;

            const updateProductRequest: UpdateProductRequest = {
                productId: currentProductDetails.productId,
                label: values.label,
                image: values.image,
                description: values.description,
                stock: values.stock,
                price: values.price,
                stockStatus: values.stockStatus,
                businessId: currentProducerBusiness?.businessId
            }

            updateCurrentProduct(updateProductRequest);

            formik.resetForm();
        }
    })

    return (
        <div className="w-full h-full flex flex-col items-center justify-center">
            <div className="w-full flex items-center max-w-4xl mb-4">
                <Link to="/producer/inventory" className="text-sm text-gray-700">
                    <ChevronLeftIcon />
                    Retour
                </Link>
            </div>
            <div className="bg-white rounded-3xl shadow-lg p-8 max-w-4xl w-full flex flex-col md:flex-row gap-8">
                <div className="shrink-0">
                    <img
                        src={formik.values.image}
                        alt="product-image"
                        className="w-72 h-72 object-cover rounded-2xl"
                    />
                </div>
                <form
                    onSubmit={formik.handleSubmit}
                    className="w-full h-full flex flex-col gap-5 justify-cente"
                >
                    <TextField
                        label="Label"
                        name="label"
                        onChange={formik.handleChange}
                        value={formik.values.label}
                        error={formik.touched.label && !!formik.errors.label}
                        helperText={formik.touched.label && formik.errors.label}
                        required
                    />
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
                        label="Description"
                        name="description"
                        multiline
                        rows={5}
                        onChange={formik.handleChange}
                        value={formik.values.description}
                        error={formik.touched.description && !!formik.errors.description}
                        helperText={formik.touched.description && formik.errors.description}
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
                    <TextField
                        label="Prix"
                        name="price"
                        onChange={formik.handleChange}
                        value={formik.values.price}
                        error={formik.touched.price && !!formik.errors.price}
                        helperText={formik.touched.price && formik.errors.price}
                        required
                    />
                    <Select
                        label="Status du stock"
                        name="unite Mesure"
                        value={formik.values.stockStatus}
                        onChange={formik.handleChange}
                        error={formik.touched.stockStatus && !!formik.errors.stockStatus}
                        required
                    >
                        <MenuItem value={StockStatus.IN_STOCK}>En stock</MenuItem>
                        <MenuItem value={StockStatus.LIMITED_STOCK}>Stock limité</MenuItem>
                        <MenuItem value={StockStatus.OUT_OF_STOCK}>En rupture de stock</MenuItem>
                    </Select>
                    <Button
                        type='submit'
                        style={{
                            backgroundColor: '#B02E0C',
                            borderRadius: '0.5rem',
                            color: 'white',
                        }}
                    >
                        <span>Enregistrer</span>
                    </Button>
                </form>
            </div>

            {toast && (
                <Toast
                    message={toast.message}
                    type={toast.type}
                    onClose={() => setToast(null)}
                />
            )}
        </div>
    );
};
