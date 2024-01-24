import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const MoimWrite = () => {
  const navigate = useNavigate();

  const [moim, setMoim] = useState({
    title: '',
    created: '',
    contents: '',
  });

  const { title, created: created, contents } = moim; //비구조화 할당

  const onChange = (event) => {
    const { value, name } = event.target; //event.target에서 name과 value만 가져오기
    setMoim({
      ...moim,
      [name]: value,
    });
  };

  const saveMoim = async () => {
    await axios.post(`//localhost:8081/moim`, moim).then((res) => {
      alert('등록되었습니다.');
      navigate('/moim');
    });
  };

  const backToList = () => {
    navigate('/moim');
  };

  return (
    <div>
      <div>
        <span>제목</span>
        <input type="text" name="title" value={title} onChange={onChange} />
      </div>
      <br />
      <div>
        <span>작성자</span>
        <input
          type="text"
          name="created"
          value={created}
          onChange={onChange}
        />
      </div>
      <br />
      <div>
        <span>내용</span>
        <textarea
          name="contents"
          cols="30"
          rows="10"
          value={contents}
          onChange={onChange}
        ></textarea>
      </div>
      <br />
      <div>
        <button onClick={saveMoim}>저장</button>
        <button onClick={backToList}>취소</button>
      </div>
    </div>
  );
};

export default MoimWrite;