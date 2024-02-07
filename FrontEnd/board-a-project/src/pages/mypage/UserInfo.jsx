import React, { useState } from "react";
import {
  Button,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from "@mui/material";

const UserInfo = () => {
  const [gender, setGender] = useState("");
  const [birthdate, setBirthdate] = useState("");
  const [selectedImage, setSelectedImage] = useState(null);

  const handleGenderChange = (event) => {
    setGender(event.target.value);
  };

  const handleBirthdateChange = (event) => {
    setBirthdate(event.target.value);
  };

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setSelectedImage(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSave = () => {
    // Save the user profile changes here
  };

  return (
    <div className="max-w-md mx-auto p-4 b rounded-2xl border-4">
      {/* 프로필 이미지 미리보기 */}
      {selectedImage && (
        <img
          src={selectedImage}
          alt="프로필 이미지"
          className="max-w-xxs max-h-xxs mb-4"
          style={{ width: "30rem" }}
        />
      )}
      {/* 프로필 이미지 업로드 */}
      <input
        type="file"
        accept="image/*"
        onChange={handleImageChange}
        className="mb-4"
      />
      {/* 나머지 입력 필드들 */}
      <div className="mb-4">
        <label htmlFor="username">아이디</label>
        <input
          type="text"
          id="username"
          placeholder="아이디"
          className="w-full px-3 py-2 rounded border"
        />
      </div>
      <div className="mb-4">
        <label htmlFor="password">비밀번호</label>
        <input
          type="password"
          id="password"
          placeholder="비밀번호"
          className="w-full px-3 py-2 rounded border"
        />
      </div>
      <div className="mb-4">
        <label htmlFor="nickname">닉네임</label>
        <input
          type="text"
          id="nickname"
          placeholder="닉네임"
          className="w-full px-3 py-2 rounded border"
        />
      </div>
      <div className="mb-4">
        <label htmlFor="contact">연락처</label>
        <input
          type="text"
          id="contact"
          placeholder="연락처"
          className="w-full px-3 py-2 rounded border"
        />
      </div>
      <FormControl fullWidth className="mb-4">
        <InputLabel id="gender-label">성별</InputLabel>
        <Select
          labelId="gender-label"
          id="gender"
          value={gender}
          onChange={handleGenderChange}
          className="w-full px-3 py-2 rounded border"
          sx={{ width: "100%", height: "2.5rem" }}
        >
          <MenuItem value="male">남성</MenuItem>
          <MenuItem value="female">여성</MenuItem>
        </Select>
      </FormControl>
      <div className="mb-4">
        <label htmlFor="birthdate">생년월일</label>
        <input
          type="date"
          id="birthdate"
          placeholder="생년월일"
          className="w-full px-3 py-2 rounded border"
          value={birthdate}
          onChange={handleBirthdateChange}
          min="1900-01-01" // 최소 날짜
          max={new Date().toISOString().split("T")[0]} // 최대 날짜 - 오늘 날짜 만들어서 년 월 일만 분리해냄
        />
      </div>
      <Button onClick={handleSave} color="primary" className="">
        수정하기
      </Button>
    </div>
  );
};

export default UserInfo;
