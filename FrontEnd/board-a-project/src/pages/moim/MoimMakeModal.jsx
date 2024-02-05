import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Modal from 'react-modal';
import { saveMoim } from '../../api/moimAPI'
import Calendar from 'react-calendar';
import { TimePicker } from '@material-ui/pickers';
import { MuiPickersUtilsProvider } from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';
import 'react-calendar/dist/Calendar.css';
import moment from 'moment';
import { useRecoilState } from 'recoil';
import { moimState } from '../../recoil/atoms/moimState';
import { locationState } from '../../recoil/atoms/moimState';


const MoimMakeModal = ({ isOpen, onRequestClose }) => {
  const navigate = useNavigate();
  const [moim, setMoim] = useRecoilState(moimState);
  const [location] = useRecoilState(locationState);

  const [date, setDate] = useState(new Date());
  const [time, setTime] = useState('10:00');

  const [isCalendarVisible, setCalendarVisible] = useState(false);

  const onDateChange = date => {
    setDate(date);
    setMoim(prevMoim => ({
      ...prevMoim,
      datetime: moment(date).format('YYYY-MM-DD') + 'T' + time + ':05',
    }));
  };

  // const onTimeChange = timeValue => {
  //   setTime(timeValue);
  //   setMoim(prevMoim => ({
  //     ...prevMoim,
  //     datetime: moment(date).format('YYYY-MM-DD') + 'T' + timeValue + ':05',
  //   }));
  // };

  const onTimeChange = timeValue => {
    const newDate = new Date();
    newDate.setHours(timeValue.getHours());
    newDate.setMinutes(timeValue.getMinutes());
    setDate(newDate);
    setMoim(prevMoim => ({
      ...prevMoim,
      datetime: moment(newDate).format('YYYY-MM-DDTHH:mm:ss'),
    }));
  };

  const onChange = event => {
    const { name, value } = event.target;
    setMoim(prevMoim => ({
      ...prevMoim,
      [name]: name === 'number' ? Number(value) : value,
    }));
  };

  const toggleCalendar = () => {
    setCalendarVisible(!isCalendarVisible);
  };

  const saveMoimData = async () => {
    console.log(moim);
    try {
      await saveMoim(moim);
      alert('등록되었습니다.');
      navigate('/moim/list', { state: { updated: true } });
      onRequestClose();
    } catch (error) {
      console.error('모임 저장 중 에러가 발생했습니다:', error);
    }
  };

  const backToList = () => {
    navigate('/moim/list');
    onRequestClose();
  };

  useEffect(() => {
    setMoim(prevMoim => ({
      ...prevMoim,
      location,
    }));
  }, [location, setMoim]);

  return (
    <MuiPickersUtilsProvider utils={DateFnsUtils}>
    <Modal isOpen={isOpen} onRequestClose={onRequestClose} className="fixed inset-0 flex items-center justify-center z-50">
      <div className="bg-white p-6 rounded-lg shadow-xl w-2/3 h-1/1">
        <div className="flex items-center space-x-2">
          <span className="font-bold text-lg">제목</span>
          <input type="text" name="title" value={moim.title} onChange={onChange} className="border rounded py-2 px-4" />
        </div>
        <div className="flex items-center space-x-2">
          <label className="font-bold text-lg">
            인원:
            <select value={moim.number} name="number" onChange={onChange} className="ml-2 border rounded py-1">
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
        <button onClick={toggleCalendar}>달력 보기</button>
        <div className="text-gray-500 mt-4">
          {moment(moim.datetime).format("YYYY년 MM월 DD일")} 
        </div>
        {isCalendarVisible && (
          <div>
            <Calendar onChange={onDateChange} value={new Date(moim.datetime)} />
            
          </div>
        )}
      </div>
      <div>
        시간:
        <TimePicker
          autoOk
          ampm={false}
          variant="inline"
          value={date}
          onChange={onTimeChange}
          style={{ width: '100%' }}
        />
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
      <div className="flex items-center space-x-2">
        <button onClick={saveMoimData} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">저장</button>
        <button onClick={backToList} className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">취소</button>
      </div>
    </div>
    </Modal>
    </MuiPickersUtilsProvider>
  );
};

export default MoimMakeModal;