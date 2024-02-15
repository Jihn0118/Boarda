import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const items = [
  { name: "가라아게", src: "src/img/가라아게.jpg" },
  { name: "감자칩", src: "src/img/감자칩.jpg" },
  // Add all your items here...
];

function FoodGame() {
  const [foods, setFoods] = useState([]);
  const [displays, setDisplays] = useState([]);
  const [winners, setWinners] = useState([]);
  const [round, setRound] = useState(64);
  const [WorldTitle, setWorldTitle] = useState("세계 음식 월드컵 64강");

  useEffect(() => {
    const shuffledItems = items.sort(() => Math.random() - 0.5);
    setFoods(shuffledItems);
    setDisplays([shuffledItems[0], shuffledItems[1]]);
  }, []);

  const clickHandler = (food) => {
    if (foods.length <= 2) {
      if (winners.length === 0) {
        setDisplays([food]);
        setRound(1);
        setWorldTitle("우승!! " + food.name);
      } else {
        const updatedFood = [...winners, food];
        setFoods(updatedFood.sort(() => Math.random() - 0.5));
        setDisplays([updatedFood[0], updatedFood[1]]);
        setWinners([]);
        setRound(round / 2);
      }
    } else if (foods.length > 2) {
      setWinners([...winners, food]);
      setDisplays([foods[2], foods[3]]);
      setFoods(foods.slice(2));
    }
  };

  return (
    <div className="res-back">
      <div>
        <Link to="/home" className="tmx-2 pr-4 pt-2.5 px-6 sm:px-8 lg:px-3 text-black text-4xl font-bold md:text-4xl hover:text-gray-700">
          <span> WorldFood </span>
        </Link>
      </div>
      <p className="title">{WorldTitle} ({round}/64)</p>
      <div className="flex-box">
        {displays.map((d) => (
          <div key={d.name} onClick={() => clickHandler(d)} className="flex-1">
            <img className="food-img" src={d.src} alt={d.name} />
            <div className="name">{d.name}</div>
          </div>
        ))}
      </div>
      <div className="footer-back">
        <footer className="py-6 text-white">
          <div className="container mx-auto text-center">
            <p>
              Project By:
              <a href="https://themewagon.com/" className="text-blue-500 underline">Jieun&Dongheon</a>
            </p>
          </div>
        </footer>
      </div>
    </div>
  );
}

export default FoodGame;