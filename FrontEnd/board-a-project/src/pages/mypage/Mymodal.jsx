import React, { useEffect, useState } from "react";
import { Modal, TextField, Button } from "@mui/material";
import { Box } from "@mui/system";
import reviewAPI from "../../api/reviewAPI";

function MyModal() {
  const [open, setOpen] = useState(false);
  const [userNum, setUserNum] = useState("");
  const [rate, setRate] = useState("");
  const [content, setContent] = useState("");
  const [cafeId, setCafeId] = useState("");
  const [moimId, setMoimId] = useState("");
  const [images, setImages] = useState([]);

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

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
      onSubmit={handleSubmit}
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
      <Button type="submit">제출</Button>
    </Box>
  );

  return (
    <div>
      <Button variant="contained" color="primary" onClick={handleOpen}>
        피드작성
      </Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
      >
        {body}
      </Modal>
    </div>
  );
}

export default MyModal;
