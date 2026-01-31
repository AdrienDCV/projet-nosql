import { Swiper, SwiperSlide } from "swiper/react";
import { Autoplay, Navigation } from "swiper/modules";
import "swiper/swiper-bundle.css";
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';

export function Carousel() {
  const images = [
    "https://www.idee-cuisine.fr/wp-content/uploads/2024/03/legumes-de-saisons-par-mois.jpg",
    "https://i.notretemps.com/1200x628/smart/2023/01/17/illustration-de-viande.jpeg",
    "https://as2.ftcdn.net/jpg/00/05/65/45/1000_F_5654547_HDQvVBX3yVfDn0Xek0Nf2o5tDYhqDm5L.jpg"
  ];

  return (
      <div className="relative w-full flex flex-row items-center">
        {/* Boutons */}
        <button className="swiper-button-prev-custom absolute left-4 z-10">
          <ArrowBackIosIcon className="text-white" />
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
        <button className="swiper-button-next-custom absolute right-4 z-10">
          <ArrowForwardIosIcon className="text-white"/>
        </button>
      </div>
  );
}
