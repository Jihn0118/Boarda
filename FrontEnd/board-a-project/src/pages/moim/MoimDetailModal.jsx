import React from 'react';
import Modal from 'react-modal';
import { useRecoilValue } from 'recoil';
import { useNavigate } from 'react-router-dom';
import { moimListState } from '../../recoil/atoms/moimState'; 
import { joinMoim } from '../../api/moimAPI';

const MoimDetailModal = ({ moimId, isOpen, onRequestClose }) => {
  const moimList = useRecoilValue(moimListState);
  const moim = moimList.find(m => m.id === moimId);
  const navigate = useNavigate();
  const join = {
    moimId: moimId,
    memberId: "b@b.com"
  };
  
  const joinMoimHandler = async () => {
    console.log(join);
    try {
      const response = await joinMoim(join);
      console.log(response)
      if (response === 0) { 
        alert('등록되었습니다.');
        navigate('/moim/list');
      } else if (response === 1) {
        alert('모임 정보가 없습니다.')
        console.error('모임 정보가 없습니다.');
      } else if (response === 2) {    
        alert('유효하지 않은 유저입니다')
        console.error('유효하지 않은 유저입니다');
      } else if (response === 3) {
        alert('이미 꽊 찬 방입니다.')
        console.error('이미 꽊 찬 방입니다.');
      } else if (response === 4) {
        alert('이미 참여 중인 방입니다')
        console.error('이미 참여 중인 방입니다');
      }
    } catch (error) {
      console.error('모임 저장 중 에러가 발생했습니다:', error);
    }
  };

  if (!moim) {
    return <div>모임 정보를 찾을 수 없습니다.</div>;
  }

  return (
    <Modal isOpen={isOpen} onRequestClose={onRequestClose}>
      <h1>{moim.title}</h1>
      <p>{moim.id}</p>
      <p>{moim.datetime}</p>
      <p>{moim.currentNumber}/{moim.number}</p>
      <p>{moim.content}</p>
      <br />
      <button onClick={joinMoimHandler}>참여</button>
      <button onClick={onRequestClose}>취소</button>
    </Modal>
  );
};

export default MoimDetailModal;