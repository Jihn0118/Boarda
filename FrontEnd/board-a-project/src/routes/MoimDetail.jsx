import React from 'react';
import { useParams } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import { moimListState } from '../state/moimState';
import { joinMoim } from '../api/moimAPI';

const MoimDetail = () => {
  const { id } = useParams();
  const moimId = parseInt(id, 10); // URL 파라미터는 문자열이므로 숫자로 변환
  const moimList = useRecoilValue(moimListState);
  const moim = moimList.find(m => m.id === moimId);
  const join = {
    moimId: moimId,
    memberId: ''
};

  const joinMoimHandler = async () => {
    console.log(moim);
    try {
      const response = await joinMoim(join);
      
      if (response.status === 200) { 
        alert('등록되었습니다.');
        navigate('/moim');
      } else {
        // 서버에서 정상적인 응답을 주었지만, 요청 자체가 실패한 경우의 로직입니다.
        console.error('이미 꽊 찬 방입니다.');
      }
    } catch (error) {
      console.error('모임 저장 중 에러가 발생했습니다:', error);
    }
  };


  const backToList = () => {
    navigate('/moim');
  };

  if (!moim) {
    return <div>모임 정보를 찾을 수 없습니다.</div>;
  }

  return (
    <div>
      <h1>{moim.title}</h1>
      <p>{moim.id}</p>
      <p>{moim.datetime}</p>
      <p>temp/{moim.number}</p>
      <p>{moim.content}</p>
      <br />
      <button onClick={joinMoimHandler}>참여</button>
      <button onClick={backToList}>취소</button>
    </div>
  );
};

export default MoimDetail;