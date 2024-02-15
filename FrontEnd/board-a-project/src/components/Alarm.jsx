import Badge from "@mui/material/Badge";
import NotificationsIcon from "@mui/icons-material/Notifications";
import { alarmState } from "../recoil/atoms/alarmState";
import { useRecoilState, useRecoilValue } from "recoil";
import { loginUserState } from "../recoil/atoms/userState";
import { useEffect } from "react";
import { alarmAPI } from "../api/alarmAPI";

export default function Alarm() {
  // recoil에 저장된 alarm상태를 가져와서 구독
  // 흐름 : 로그인 요청 -> JWT발급 -> SSE요청 -> 수신 시 recoilVal갱신 -> 재랜더링
  const [alarmData, setAlarmData] = useRecoilState(alarmState);
  const loginUser = useRecoilValue(loginUserState);
  const localURL = "http://localhost:8081/api/alarm/subscribe";
  const remoteURL = "https://boarda.site/api/alarm/subscribe";

  useEffect(() => {
    // 로그인 되어있는 경우 sse 요청
    if (!!loginUser.id) {
      const sse = new EventSource(`${localURL}`, { withCredentials: true });


      // 연결 성공 시 메시지 수신
      sse.addEventListener("connect", (e) => {
        console.log(e);
      });


      // 최신알람갯수
      sse.addEventListener("latestAlarm", (e) => {
        console.log(e);
      });

      // 알람갯수
      sse.addEventListener("alarmCount", (e) => {
        console.log(e);
      });

      // 새로운 알람 도착
      sse.addEventListener("addAlarm", (e) => {
        console.log(e);
      });



      // 서버에서 메시지 도착 할 때
      sse.onmessage = (event) => {
        console.log(`실시간알람\n${event}`);
        console.log(event);

        setAlarmData([...alarmData, e.data]);
      };
      sse.onerror = (e) => {
        console.log("sse에러");
        console.log(e);
      };
      sse.onopen = async (e) => {
        console.log("sse 연결됨");
        await alarmAPI.getStart();
        await alarmAPI.getList();


        setAlarmData([...alarmData, e.data]);
      };
    }
  }, [loginUser]);

  return (
    <Badge badgeContent={alarmData?.length}>
      <NotificationsIcon color="action"></NotificationsIcon>
    </Badge>
  );
}
