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
import { Box, Button, Modal, TextField } from "@mui/material";

export default function GroupHistory() {
  const [history, setHistory] = useState([]);
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const params = useParams();

  useEffect(() => {
    // 랜더링 될 때 그룹이력  요청
    // 비동기로 받아오고 랜더링할 것 (유저 id 기준 get 요청)
    // 백 로그인 완성되면 파람에서 로그인 유저 기준으로 로직 고치기
    const fetchData = async () => {
      try {
        const jwt = JSON.parse(localStorage.getItem("loginUser")).jwt;
        const res = await moimAPI.getMoimHistory(params.userId, jwt);
        console.log(res);
        console.log(res.data);
        setHistory(res.data);
      } catch (err) {
        console.log("모임 이력 받아오기 실패");
      }
    };
    fetchData();
  }, []);

  // 피드남기기 모달
  function ReviewModal() {
    const [userNum, setUserNum] = useState("");
    const [rate, setRate] = useState("");
    const [content, setContent] = useState("");
    const [cafeId, setCafeId] = useState("");
    const [moimId, setMoimId] = useState("");
    const [images, setImages] = useState([]);

    const handleChange = (e) => {
      setImages([...e.target.files]);
    };

    const handleSubmit = async (e) => {
      e.preventDefault();
      const formData = new FormData();

      let user = {
        userNum: userNum,
        rate: rate,
        content: content,
        cafeId: cafeId,
        moimId: moimId,
      };
      formData.append(
        "review",
        new Blob([JSON.stringify(user)], { type: "application/json" })
      );

      images.forEach((image) => {
        formData.append("images", image);
      });

      try {
        const res = await reviewAPI.registMyReview(formData);
        console.log(res.data);
        handleClose();
      } catch (error) {
        console.error(error);
      }
    };
    const body = (
      <Box
        component="form"
        sx={{
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          width: 400,
          bgcolor: "white",
          border: "2px solid #000",
          boxShadow: 24,
          p: 4,
        }}
      >
        <TextField
          fullWidth
          required
          value={userNum}
          onChange={(e) => setUserNum(e.target.value)}
          label="유저 Num"
        />
        <TextField
          fullWidth
          required
          value={rate}
          onChange={(e) => setRate(e.target.value)}
          label="평점"
        />
        <TextField
          fullWidth
          required
          value={content}
          onChange={(e) => setContent(e.target.value)}
          label="후기 내용"
        />
        <TextField
          fullWidth
          required
          value={cafeId}
          onChange={(e) => setCafeId(e.target.value)}
          label="카페 ID"
        />
        <TextField
          fullWidth
          required
          value={moimId}
          onChange={(e) => setMoimId(e.target.value)}
          label="모임 ID"
        />
        <input type="file" multiple onChange={handleChange} />
        <Button onClick={handleSubmit}>제출</Button>
      </Box>
    );
    return (
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
      >
        {body}
      </Modal>
    );
  }

  // 함께한 플레이어 모달
  function teammateModal() {
    const [userNum, setUserNum] = useState("");
    const [rate, setRate] = useState("");
    const [content, setContent] = useState("");
    const [cafeId, setCafeId] = useState("");
    const [moimId, setMoimId] = useState("");
    const [images, setImages] = useState([]);

    const handleChange = (e) => {
      setImages([...e.target.files]);
    };

    const handleSubmit = async (e) => {
      e.preventDefault();
      const formData = new FormData();

      let user = {
        userNum: userNum,
        rate: rate,
        content: content,
        cafeId: cafeId,
        moimId: moimId,
      };
      formData.append(
        "review",
        new Blob([JSON.stringify(user)], { type: "application/json" })
      );

      images.forEach((image) => {
        formData.append("images", image);
      });

      try {
        const res = await reviewAPI.registMyReview(formData);
        console.log(res.data);
        handleClose();
      } catch (error) {
        console.error(error);
      }
    };
    const body = (
      <Box
        component="form"
        sx={{
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          width: 400,
          bgcolor: "white",
          border: "2px solid #000",
          boxShadow: 24,
          p: 4,
        }}
      >
        <TextField
          fullWidth
          required
          value={userNum}
          onChange={(e) => setUserNum(e.target.value)}
          label="유저 Num"
        />
        <TextField
          fullWidth
          required
          value={rate}
          onChange={(e) => setRate(e.target.value)}
          label="평점"
        />
        <TextField
          fullWidth
          required
          value={content}
          onChange={(e) => setContent(e.target.value)}
          label="후기 내용"
        />
        <TextField
          fullWidth
          required
          value={cafeId}
          onChange={(e) => setCafeId(e.target.value)}
          label="카페 ID"
        />
        <TextField
          fullWidth
          required
          value={moimId}
          onChange={(e) => setMoimId(e.target.value)}
          label="모임 ID"
        />
        <input type="file" multiple onChange={handleChange} />
        <Button onClick={handleSubmit}>제출</Button>
      </Box>
    );
    return (
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
      >
        {body}
      </Modal>
    );
  }

  return (
    <>
      <div className="px-32 mx-auto text-center">
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell align="center" className="w-1/12">
                  방번호
                </TableCell>
                <TableCell align="center" className="w-2/3">
                  방 제목
                </TableCell>
                <TableCell align="center" className="w-1/12">
                  인원
                </TableCell>
                <TableCell align="center" className="w-1/12">
                  함께한 유저
                </TableCell>
                <TableCell align="center" className="w-1/12">
                  피드남기기
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {history &&
                history.map((e, index) => (
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
                      <button className="bg-blue-200" onClick={handleOpen}>
                        피드남기기
                      </button>
                    </TableCell>
                  </TableRow>
                ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
      <ReviewModal></ReviewModal>
    </>
  );
}
