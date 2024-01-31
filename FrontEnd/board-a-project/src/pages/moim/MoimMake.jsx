import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { saveMoim } from '../../api/moimAPI'
import Calendar from 'react-calendar';
import { TimePicker } from 'react-ios-time-picker';
import 'react-calendar/dist/Calendar.css';
import moment from 'moment';
import { useRecoilState } from 'recoil';
import { moimState } from '../../recoil/atoms/moimState';
import { locationState } from '../../recoil/atoms/moimState';


const MoimMake = () => {
  const navigate = useNavigate();
  const [moim, setMoim] = useRecoilState(moimState);
  const [location] = useRecoilState(locationState);

  const [date, setDate] = useState(new Date());
  const [time, setTime] = useState('10:00');

  const onDateChange = date => {
    setDate(date);
    setMoim(prevMoim => ({
      ...prevMoim,
      datetime: moment(date).format('YYYY-MM-DD') + 'T' + time + ':05',
    }));
  };

  const onTimeChange = timeValue => {
    setTime(timeValue);
    setMoim(prevMoim => ({
      ...prevMoim,
      datetime: moment(date).format('YYYY-MM-DD') + 'T' + timeValue + ':05',
    }));
  };

  const onChange = event => {
    const { name, value } = event.target;
    setMoim(prevMoim => ({
      ...prevMoim,
      [name]: name === 'number' ? Number(value) : value,
    }));
  };

  const saveMoimData = async () => {
    console.log(moim);
    try {
      await saveMoim(moim);
      alert('등록되었습니다.');
      navigate('/moim/list');
    } catch (error) {
      console.error('모임 저장 중 에러가 발생했습니다:', error);
    }
  };

  const backToList = () => {
    navigate('/moim/list');
  };

  useEffect(() => {
    setMoim(prevMoim => ({
      ...prevMoim,
      location,
    }));
  }, [location, setMoim]);

  return (
    <div>
      <div>
        <span>제목</span>
        <input type="text" name="title" value={moim.title} onChange={onChange} />
      </div>
      <br />
      <div>
        <label>
        인원:
        <select value={moim.number} name="number" onChange={onChange}>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
        </select>
      </label>
      </div>
      <div>
        날짜:
        <Calendar onChange={onDateChange} value={new Date(moim.datetime)} />
        <div className="text-gray-500 mt-4">
           {moment(moim.datetime).format("YYYY년 MM월 DD일")} 
         </div>
      </div>
      <div>
        시간:
        <TimePicker onChange={onTimeChange} value={time} />
      </div>

      <br />
      <div>
        <span>내용</span>
        <textarea
          name="content"
          cols="30"
          rows="10"
          value={moim.content}
          onChange={onChange}
        ></textarea>
      </div>
      <br />
      <div>
      <button onClick={saveMoimData}>저장</button>
        <button onClick={backToList}>취소</button>
      </div>
    </div>
  );
};

export default MoimMake;