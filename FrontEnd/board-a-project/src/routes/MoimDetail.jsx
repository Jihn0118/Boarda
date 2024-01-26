import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import Moim from '../components/Moim';

const MoimDetail = () => {
  const { id } = useParams(); // /board/:idx와 동일한 변수명으로 데이터를 꺼낼 수 있습니다.
  const [loading, setLoading] = useState(true);
  const [moim, setMoim] = useState({});
  const getMoim = async () => {
    const resp = await (await axios.get(`//localhost:8081/moim/${id}`)).data;
    setMoim(resp.data);
    setLoading(false);
  };

  useEffect(() => {
    getMoim();
  }, []);

  return (
    <div>
      {loading ? (
        <h2>loading...</h2>
      ) : (
        <Moim
          idx={moim.id}
          title={moim.title}
          contents={moim.contents}
          createdBy={moim.created}
        />
      )}
    </div>
  );
};

export default MoimDetail;