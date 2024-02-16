import React, { useState } from "react";
import { TextField, Button, FormControl, InputLabel, Select, MenuItem } from "@mui/material";

const UserInfo = () => {
  const [gender, setGender] = React.useState("");
  const [birthdate, setBirthdate] = React.useState("");
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


  };

  return (
    <div>
      <h1>회원 정보 수정</h1>
      {/* 프로필 이미지 업로드 */}
      <input
        type="file"
        accept="image/*"
        onChange={handleImageChange}
        style={{ marginBottom: '10px' }}
      />
      {/* 프로필 이미지 미리보기 */}
      {selectedImage && (
        <img src={selectedImage} alt="프로필 이미지" style={{ maxWidth: '200px', maxHeight: '200px', marginBottom: '10px' }} />
      )}
      {/* 나머지 입력 필드들 */}
      <TextField margin="dense" id="username" label="아이디" fullWidth />
      <TextField margin="dense" id="password" label="비밀번호" type="password" fullWidth />
      <TextField margin="dense" id="nickname" label="닉네임" fullWidth />
      <TextField margin="dense" id="contact" label="연락처" fullWidth />
      <FormControl fullWidth margin="dense">
        <InputLabel id="gender-label">성별</InputLabel>
        <Select
          labelId="gender-label"
          id="gender"
          value={gender}
          onChange={handleGenderChange}
        >
          <MenuItem value="male">남성</MenuItem>
          <MenuItem value="female">여성</MenuItem>
        </Select>
      </FormControl>
      <TextField
        margin="dense"
        id="birthdate"
        label="생년월일"
        type="date"
        InputLabelProps={{
          shrink: true,
        }}
        value={birthdate}
        onChange={handleBirthdateChange}
        fullWidth
      />
      <Button onClick={handleSave} color="primary">
        저장
      </Button>
    </div>
  );
};

export default UserInfo;
