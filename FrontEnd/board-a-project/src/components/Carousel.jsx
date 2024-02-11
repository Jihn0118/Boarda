import { Carousel } from "@material-tailwind/react";
export default function renderCarousel() {
  return (
    <Carousel className="rounded-xl">
      <img
        src="src/assets/images/Carousel01.png"
        alt="image 1"
        className="h-full w-full object-cover"
      />
      <img
        src="src/assets/images/Carousel02.png"
        alt="image 2"
        className="h-full w-full object-cover"
      />
    </Carousel>
  );
}
