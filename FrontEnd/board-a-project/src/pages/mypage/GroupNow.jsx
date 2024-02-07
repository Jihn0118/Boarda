import React, { useEffect, useState } from "react";
import myPageAPI from "../../api/mypageAPI";
import { useRecoilValue } from "recoil";
import { loginUserState } from "../../recoil/atoms/userState";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";

export default function GroupNow() {
  const loginUser = useRecoilValue(loginUserState);
  const [participatingGroup, setParticipatingGroup] = useState({});

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await myPageAPI.getParticipatingMoim(loginUser.id);
        setParticipatingGroup(res.data);
      } catch (e) {
        console.log(e);
      }
    };
  }, [loginUser.id]);

  // mui table을 이용해서 구현
  // row 값을 participatingGroup 객체 그대로
  const row = participatingGroup;

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
            key={row.name}
            sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
          >
            <TableCell component="th" scope="row">
              {row.name}
            </TableCell>
            <TableCell align="right">{row.calories}</TableCell>
            <TableCell align="right">{row.fat}</TableCell>
            <TableCell align="right">
              <button className="bg-gray-200">나가기버튼</button>
            </TableCell>
          </TableRow>
        </TableBody>
      </Table>
    </TableContainer>
  );

  return table;
}
