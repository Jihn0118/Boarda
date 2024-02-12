import Badge from "@mui/material/Badge";
import NotificationsIcon from "@mui/icons-material/Notifications";
import { alarmState } from "../recoil/atoms/alarmState";
import { useRecoilValue } from "recoil";

export default function Alarm() {
  // recoil에 저장된 alarm상태를 가져와서 구독
  // 흐름 : 로그인 요청 -> JWT발급 -> SSE요청 -> 수신 시 recoilVal갱신 -> 재랜더링
  const alarms = useRecoilValue(alarmState);

  return (
    <Badge badgeContent={alarms.length}>
      <NotificationsIcon color="action"></NotificationsIcon>
    </Badge>
  );
}
