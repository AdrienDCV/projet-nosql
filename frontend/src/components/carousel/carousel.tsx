import { Swiper, SwiperSlide } from "swiper/react";
import { Autoplay, Navigation } from "swiper/modules";
import "swiper/swiper-bundle.css";

import img1 from "/src/assets/images/caroussel_1.jpg";
import img2 from "/src/assets/images/caroussel_2.jpg";
import img3 from "/src/assets/images/caroussel_3.jpg";

export function Carousel() {
  const images = [img1, img2, img3];

  return (
      <div className="relative w-full flex flex-row items-center">
        {/* Boutons */}
        <button className="swiper-button-prev-custom absolute left-4 z-10 bg-white p-3 rounded-full shadow">
          ◀
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
            className="w-full"
        >
          {images.map((src, idx) => (
              <SwiperSlide key={idx}>
                <div className="w-full h-[400px] overflow-hidden">
                  <img
                      src={src}
                      alt={`Slide ${idx}`}
                      className="w-full h-full object-cover"
                  />
                </div>
              </SwiperSlide>
          ))}
        </Swiper>
        <button className="swiper-button-next-custom absolute right-4 z-10 bg-white p-3 rounded-full shadow">
          ▶
        </button>
      </div>
  );
}
