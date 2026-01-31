import React from "react";

type ToastProps = {
    message: string;
    type?: "success" | "error";
    onClose: () => void;
};

export const Toast: React.FC<ToastProps> = ({ message,type = "success",onClose,}) => {
    const colors = {
        success: "bg-green-500",
        error: "bg-red-500",
    };

    return (
        <div className="fixed top-5 right-5 z-50 animate-fade-in">
            <div
                className={`${colors[type]} text-white px-6 py-3 rounded-2xl shadow-lg flex items-center gap-4`}
            >
                <span className="text-sm font-medium">{message}</span>

                <button
                    onClick={onClose}
                    className="text-white text-lg leading-none hover:opacity-70"
                >
                    ×
                </button>
            </div>
        </div>
    );
};
