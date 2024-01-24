import React from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Moim = ({ idx, title, contents, createdBy }) => {
  const navigate = useNavigate();

  const moveToUpdate = () => {
    navigate('/update/' + idx);
  };

  const deleteMoim = async () => {
    if (window.confirm('게시글을 삭제하시겠습니까?')) {
      await axios.delete(`//localhost:8081/moim/${idx}`).then((res) => {
        alert('삭제되었습니다.');
        navigate('/moim');
      });
    }
  };

  const moveToList = () => {
    navigate('/moim');
  };

  return (
    <div>
      <div>
        <h2>{title}</h2>
        <h5>{createdBy}</h5>
        <hr />
        <p>{contents}</p>
      </div>
      <div>
        <button onClick={moveToUpdate}>수정</button>
        <button onClick={deleteMoim}>삭제</button>
        <button onClick={moveToList}>목록</button>
      </div>
    </div>
  );
};

export default Moim;