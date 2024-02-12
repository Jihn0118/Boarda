import React, { useEffect, useState } from "react";
import {
  Button,
  TextField,
  Radio,
  RadioGroup,
  FormControlLabel,
  FormControl,
  FormLabel,
  Box,
  Card,
} from "@mui/material";
import { userAPI } from "../../api/userAPI";

export default function SignUp() {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [nickname, setNickname] = useState();
  const [birthday, setBirthday] = useState();
  const [gender, setGender] = useState();
  const [profileImage, setProfileImage] = useState(null);

  const [isEmailOk, setIsEmailOk] = useState(false);
  const [isNicknameOk, setIsNicknameOk] = useState(false);

  // 중복 체크했는데 값 바꾸면 ? 다시 체크하도록
  useEffect(() => {
    if (isEmailOk === true) {
      setIsEmailOk(!email);
    }
  }, [email]);

  useEffect(() => {
    if (isNicknameOk === true) {
      setIsNicknameOk(!isNicknameOk);
    }
  }, [nickname]);

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setProfileImage(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };

  async function handleEmailVerification() {
    try {
      const res = await userAPI.checkId(email);
      // 스프링에서 HttpStatus.ACCEPTED로 응답 -> 202임
      console.log(res)
      if (res.status === 202) {
        setIsEmailOk(true);
        alert("사용 가능한 아이디(이메일)입니다.");
      } else {
        alert("이미 사용중인 아이디(이메일) 입니다.");
      }
    } catch (e) {
      console.log(e);
      console.log("이메일 중복체크 통신오류 발생");
    }
  }

  async function handleNicknameVerification() {
    try {
      const res = await userAPI.checkNickname(email);
      // 스프링에서 HttpStatus.ACCEPTED로 응답 -> 202임
      if (res.status === 202) {
        setIsNicknameOk(true);
        alert("사용 가능한 닉네임입니다.");
      } else {
        alert("이미 사용중인 닉네임입니다.");
      }
    } catch (e) {
      console.log(e);
      console.log("이메일 중복체크 통신오류 발생");
    }
  }
  return (
    <Box
      component="form"
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        gap: "16px",
        maxWidth: "400px",
        margin: "0 auto",
        padding: "16px",
      }}
    >
      <Card sx={{ width: "100%", padding: "16px", gap: "16px" }}>
        <Box sx={{ display: "flex", gap: "16px" }}>
          <TextField
            label="이메일"
            name="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <Button onClick={handleEmailVerification} disabled={isEmailOk}>
            {isEmailOk ? "확인됨" : "중복확인"}
          </Button>
        </Box>
        <Box sx={{ display: "flex", gap: "16px" }}>
          <TextField
            label="닉네임"
            name="nickname"
            value={nickname}
            onChange={(e) => setNickname(e.target.value)}
            required
          />
          <Button onClick={handleNicknameVerification} disabled={isNicknameOk}>
            {isNicknameOk ? "확인됨" : "중복확인"}
          </Button>
        </Box>
        <TextField
          label="비밀번호"
          name="password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <input
          label="생년월일"
          name="birthday"
          type="date"
          value={birthday}
          onChange={(e) => setBirthday(e.target.value)}
          required
          min="1900-01-01"
          max={new Date().toISOString().split("T")[0]}
        />
        <FormControl component="fieldset">
          <FormLabel component="legend">성별</FormLabel>
          <RadioGroup
            name="gender"
            value={gender}
            onChange={(e) => setGender(e.target.value)}
          >
            <FormControlLabel value="female" control={<Radio />} label="여자" />
            <FormControlLabel value="male" control={<Radio />} label="남자" />
          </RadioGroup>
        </FormControl>
        <input
          accept="image/*"
          type="file"
          name="profileImage"
          onChange={handleFileChange}
        />
        {profileImage && (
          <img
            src={profileImage}
            alt="프로필 이미지 미리보기"
            style={{ width: "100px", height: "100px" }}
          />
        )}
        <Button variant="contained">회원가입</Button>
      </Card>
    </Box>
  );
}
