import { useOutletContext } from "react-router-dom";


export default function FeedDetail() {
  const info = useOutletContext();
  console.log(info)

  

  // Outlet이라 일반적인 props로는 못 받고, useOutletContext로 받을 수 있다.
  // 상위에서 feed 상세정보를 넘겨받음

  return <div>FeedDetail</div>;
}
