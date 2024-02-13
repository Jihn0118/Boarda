import React, { useEffect, useState } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { useParams } from "react-router-dom";
import { moimAPI } from "../../api/moimAPI";
import ReviewRegistModal from "./ReviewRegistModal";

export default function GroupHistory() {
  const [history, setHistory] = useState([]);
  const params = useParams();

  useEffect(() => {
    // 랜더링 될 때 그룹이력  요청
    // 비동기로 받아오고 랜더링할 것 (유저 id 기준 get 요청)
    // 백 로그인 완성되면 파람에서 로그인 유저 기준으로 로직 고치기
    const fetchData = async () => {
      try {
        const jwt = JSON.parse(localStorage.getItem("loginUser")).jwt;
        const res = await moimAPI.getMoimHistory(params.userId,jwt);
        console.log(res.data);
        console.log(123);
        setHistory(res.data);
      } catch (err) {
        console.log("모임 이력 받아오기 실패");
      }
    };
    fetchData();
  }, []);

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
            <TableCell align="right">피드남기기</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {history.map((e, index) => (
            <TableRow
              key={e.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {e.id}
              </TableCell>
              <TableCell align="right">{e.title}</TableCell>
              <TableCell align="right">{e.number}</TableCell>
              <TableCell align="right">
                {/* Status 에 따라서 바뀌도록 수정해야 */}
                <button className="bg-blue-200">나가기버튼</button>
              </TableCell>
              <TableCell align="right">
                {/* Status 에 따라서 바뀌도록 수정해야 */}
                <button className="bg-blue-200">피드남기기</button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );

  return (
    <>
      <ReviewRegistModal />
      {table};
    </>
  );
}
