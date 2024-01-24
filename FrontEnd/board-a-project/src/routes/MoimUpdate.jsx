import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';

const MoimUpdate = () => {
  const navigate = useNavigate();
  const { id: id } = useParams(); // /update/:idx와 동일한 변수명으로 데이터를 꺼낼 수 있습니다.
  const [moim, setMoim] = useState({
    idx: 0,
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

  const getMoim = async () => {
    const resp = await (await axios.get(`//localhost:8081/moim/${id}`)).data;
    setMoim(resp.data);
  };

  const updateMoim = async () => {
    await axios.patch(`//localhost:8081/moim`, moim).then((res) => {
      alert('수정되었습니다.');
      navigate('/moim/' + id);
    });
  };

  const backToDetail = () => {
    navigate('/moim/' + id);
  };

  useEffect(() => {
    getMoim();
  }, []);

  return (
    <div>
      <div>
        <span>제목</span>
        <input type="text" name="title" value={title} onChange={onChange} />
      </div>
      <br />
      <div>
        <span>작성자</span>
        <input type="text" name="createdBy" value={created} readOnly={true} />
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
        <button onClick={updateMoim}>수정</button>
        <button onClick={backToDetail}>취소</button>
      </div>
    </div>
  );
};

export default MoimUpdate;