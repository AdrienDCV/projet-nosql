import { Swiper, SwiperSlide } from "swiper/react";
import { Autoplay, Navigation } from "swiper/modules";
import "swiper/swiper-bundle.css";

import img1 from "/src/assets/images/caroussel_1.jpg";
import img2 from "/src/assets/images/caroussel_2.jpg";
import img3 from "/src/assets/images/caroussel_3.jpg";

export function Caroussel() {
    const images = [img1, img2, img3];

    return (
        <div className="relative w-screen overflow-hidden">
            {/* Boutons */}
            <button className="swiper-button-prev-custom absolute left-4 top-1/2 -translate-y-1/2 z-10 bg-white p-3 rounded-full shadow">
                ◀
            </button>
            <button className="swiper-button-next-custom absolute right-4 top-1/2 -translate-y-1/2 z-10 bg-white p-3 rounded-full shadow">
                ▶
            </button>

            <Swiper
                modules={[Autoplay, Navigation]}
                loop
                autoplay={{ delay: 5000, disableOnInteraction: false }}
                navigation={{
                    prevEl: ".swiper-button-prev-custom",
                    nextEl: ".swiper-button-next-custom",
                }}
                slidesPerView={1}
                className="w-screen"
            >
                {images.map((src, idx) => (
                    <SwiperSlide key={idx}>
                        <img
                            src={src}
                            alt={`Slide ${idx}`}
                            className="w-full h-100 object-cover"
                        />
                    </SwiperSlide>
                ))}
            </Swiper>
        </div>
    );
}
