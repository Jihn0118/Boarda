import React, { useState, useEffect } from "react";
import Badge from "@mui/material/Badge";
import NotificationsIcon from "@mui/icons-material/Notifications";
import Popover from "@mui/material/Popover";
import { alarmState } from "../recoil/atoms/alarmState";
import { useRecoilState, useRecoilValue } from "recoil";
import { loginUserState } from "../recoil/atoms/userState";
import { alarmAPI } from "../api/alarmAPI";

export default function Alarm() {
  const [alarmData, setAlarmData] = useRecoilState(alarmState);
  const loginUser = useRecoilValue(loginUserState);
  const [anchorEl, setAnchorEl] = useState(null);
  const [alarmList, setAlarmList] = useState([]);

  const handleClick = async (event) => {
    setAnchorEl(event.currentTarget);
    try {
      const jwt = JSON.parse(localStorage.getItem("loginUser")).jwt;
      const res = await alarmAPI.getAlarmList(loginUser.id, jwt);
      setAlarmList(res.data);
    } catch (err) {
      console.log("알람 목록 받아오기 실패");
    }
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const open = Boolean(anchorEl);
  const id = open ? "simple-popover" : undefined;

  return (
    <>
      <Badge badgeContent={alarmData?.length} onClick={handleClick}>
        <NotificationsIcon color="action"></NotificationsIcon>
      </Badge>
      <Popover
        id={id}
        open={open}
        anchorEl={anchorEl}
        onClose={handleClose}
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "center",
        }}
        transformOrigin={{
          vertical: "top",
          horizontal: "center",
        }}
      >
        <div>
          {alarmList.map((alarm) => (
            <p key={alarm.id}>{alarm.message}</p>  // 알람 메시지 출력
          ))}
        </div>
      </Popover>
    </>
  );
}
