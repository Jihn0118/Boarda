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
    // <Modal isOpen={isOpen} onRequestClose={onRequestClose}>
    //   <h1>{moim.title}</h1>
    //   <p>{moim.id}</p>
    //   <p>{moim.datetime}</p>
    //   <p>{moim.currentNumber}/{moim.number}</p>
    //   <p>{moim.content}</p>
    //   <br />
    //   <button onClick={joinMoimHandler}>참여</button>
    //   <button onClick={onRequestClose}>취소</button>
    // </Modal>

    // <Modal isOpen={isOpen} onRequestClose={onRequestClose} className="fixed inset-0 flex items-center justify-center z-50">
    //   <div className="bg-white p-4 rounded w-1/2 h-1/2">
    //     <h1 className="text-2xl font-bold mb-4">{moim.title}</h1>
    //     <p className="text-sm mb-1">{moim.id}</p>
    //     <p className="text-sm mb-1">{moim.datetime}</p>
    //     <p className="text-sm mb-1">{moim.currentNumber}/{moim.number}</p>
    //     <p className="text-sm mb-4">{moim.content}</p>
    //     <button onClick={joinMoimHandler} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-2">참여</button>
    //     <button onClick={onRequestClose} className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">취소</button>
    //   </div>
    // </Modal>
    <Modal isOpen={isOpen} onRequestClose={onRequestClose} className="fixed inset-0 flex items-center justify-center z-50">
      <div className="bg-white p-6 rounded-lg shadow-xl w-2/3 h-2/3">
        <h1 className="text-2xl font-bold mb-4 text-gray-700">{moim.title}</h1>
        <p className="text-sm mb-1 text-gray-500">{moim.id}</p>
        <p className="text-sm mb-1 text-gray-500">{moim.datetime}</p>
        <p className="text-sm mb-1 text-gray-500">{moim.currentNumber}/{moim.number}</p>
        <p className="text-sm mb-4 text-gray-500">{moim.content}</p>
        <button onClick={joinMoimHandler} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-2">참여</button>
        <button onClick={onRequestClose} className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">취소</button>
      </div>
    </Modal>
  );
};

export default MoimDetailModal;