import React, { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import { loginUserState } from "../../recoil/atoms/userState";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { moimAPI } from "../../api/moimAPI";
import { useNavigate } from "react-router-dom";

export default function GroupNow() {
  const loginUser = useRecoilValue(loginUserState);
  const [participatingGroup, setParticipatingGroup] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await moimAPI.getParticipatingMoim();
        setParticipatingGroup(res.data);
        console.log(res.data);
      } catch (e) {
        console.log(e);
      }
    };
    fetchData();
  }, [loginUser.id]);

  // mui table을 이용해서 구현
  // fetch한 값을 그대로 테이블로 -> 객체는 log찍어서 확인

  // 참여중인 그룹 표로 보여주기
  const table = (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>방번호</TableCell>
            <TableCell align="right">방제</TableCell>
            <TableCell align="right">인원</TableCell>
            <TableCell align="right">나가기</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          <TableRow
            key={participatingGroup.id}
            sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
          >
            <TableCell component="th" scope="row">
              {participatingGroup.id}
            </TableCell>
            <TableCell align="right">{participatingGroup.title}</TableCell>
            <TableCell align="right">{participatingGroup.number}</TableCell>
            <TableCell align="right">
              {participatingGroup.id && (
                <button
                  onClick={() => {
                    moimAPI.exitMoim(participatingGroup.id);
                    alert("방 나가기 완료");
                    setParticipatingGroup({})
                  }}
                  className="bg-blue-200"
                >
                  나가기버튼
                </button>
              )}
            </TableCell>
          </TableRow>
        </TableBody>
      </Table>
    </TableContainer>
  );

  return table;
}
