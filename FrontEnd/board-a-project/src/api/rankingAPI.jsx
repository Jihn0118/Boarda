import api from "./api";

const END_POINT = "ranking";


const rankingAPI = {
  getRankingCafe() {
    return api({
      method: "get",
      url: `${END_POINT}/cafes`,
    });
  },
  getRankingGame() {
    return api({
      method: "get",
      url: `${END_POINT}/games`,
    });
  }
}
export default rankingAPI;
