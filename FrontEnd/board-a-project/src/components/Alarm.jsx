import Badge from "@mui/material/Badge";
import NotificationsIcon from "@mui/icons-material/Notifications";
import { alarmState } from "../recoil/atoms/alarmState";
import { useRecoilValue } from "recoil";

export default function Alarm() {
  // recoil에 저장된 alarm상태를 가져와서 구독
  // 흐름 : 로그인 요청 -> JWT발급 -> SSE요청 -> 수신 시 recoilVal갱신 -> 재랜더링
<<<<<<< Updated upstream
  const alarms = useRecoilValue(alarmState);
=======
  const [alarmData, setAlarmData] = useRecoilState(alarmState);
  const loginUser = useRecoilValue(loginUserState);

  useEffect(() => {
    // 로그인 되어있는 경우 sse 요청
    if (!!loginUser.id) {
      const sse = new EventSource("https://boarda.site/api/alarm/subscribe");
      // 서버에서 메시지 도착 할 때
      sse.onmessage = (e) => {
        setAlarmData([...alarmData, e.data]);
      };
      sse.onerror = (e) => {
        console.log("sse에러")
        console.log(e);
      };
      sse.onopen = async (e) => {
        console.log("sse 연결됨");
        let count =  await alarmAPI.getCount();
        let start =  await alarmAPI.getStart();
        console.log(count)

        console.log(start)

        setAlarmData([...alarmData, e.data]);
      };
    }
  }, [loginUser]);
>>>>>>> Stashed changes

  return (
    <Badge badgeContent={alarms.length}>
      <NotificationsIcon color="action"></NotificationsIcon>
    </Badge>
  );
}
