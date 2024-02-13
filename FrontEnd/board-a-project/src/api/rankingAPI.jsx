import api from "./api";

const END_POINT = "ranking";


const rankingAPI = {
  getRankingList() {
    return api({
      method: "get",
      url: `${END_POINT}`,
    });
  },
}
export default rankingAPI;
