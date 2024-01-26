import React, { useReducer, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Calendar from 'react-calendar';
import { TimePicker } from 'react-ios-time-picker';
import 'react-calendar/dist/Calendar.css';
import moment from 'moment';

const initialState = {
  userId: 10,
  title: '',
  content: '',
  number: 2,
  location: "서울시 강남구",
  datetime: new Date(),
  // friends: []
};

const moimReducer = (state, action) => {
  switch (action.type) {
    case 'SET_FIELD':
      return { ...state, [action.fieldName]: action.payload };
    case 'ADD_FRIEND':
      return { ...state, friends: [...state.friends, action.payload] };
    case 'REMOVE_FRIEND':
      return { ...state, friends: state.friends.filter(id => id !== action.payload) };
    default:
      return state;
  }
};

const MoimWrite = () => {
  const navigate = useNavigate();
  const [moim, dispatch] = useReducer(moimReducer, initialState);

  const [date, setDate] = useState(new Date());
  const [time, setTime] = useState('10:00');

  const onDateChange = date => {
    setDate(date);
    dispatch({
      type: 'SET_FIELD',
      fieldName: 'datetime',
      payload: moment(date).format('YYYY-MM-DD') + 'T' + time + ':05',
    });
  };

  const onTimeChange = (timeValue) => {
    setTime(timeValue);
    dispatch({
      type: 'SET_FIELD',
      fieldName: 'datetime',
      payload: moment(date).format('YYYY-MM-DD') + 'T' + timeValue + ':05',
    });
  }

  const onChange = (event) => {
    dispatch({
      type: 'SET_FIELD',
      fieldName: event.target.name,
      payload: event.target.name === 'number' ? Number(event.target.value) : event.target.value,
    });
  };

  const saveMoim = async () => {
    console.log(moim)
    try{
      await axios.post(`//localhost:8081/moim/room`, moim).then((res) => {
      alert('등록되었습니다.');
      navigate('/moim');
    });
    } catch (error) {
      console.error('모임 저장 중 에러가 발생했습니다:', error);
    }
  };


  const backToList = () => {
    navigate('/moim');
  };

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
        <button onClick={saveMoim}>저장</button>
        <button onClick={backToList}>취소</button>
      </div>
    </div>
  );
};

export default MoimWrite;


// Reducer 안쓴 버전 이었던 것

// const MoimWrite = () => {
//   const navigate = useNavigate();

//   const [moim, setMoim] = useState({
//     user_id: '',
//     title: '',
//     contents: '',
//     number: '',
//     location: '',
//     datetime: '',
//     friends: []
//   });

//   const [selectedNumber, setSelectedNumber] = useState(2);

//   const [selectedDate, setSelectedDate] = useState(new Date());

//   const { user_id, title, contents, number, location, datetime, friends } = moim; //비구조화 할당

//   const addFriend = friendId => {
//     setMoim(prevMoim => ({
//         ...prevMoim,
//         friends: [...prevMoim.friends, friendId]
//     }));
//   };

//   const removeFriend = friendId => {
//       setMoim(prevMoim => ({
//           ...prevMoim,
//           friends: prevMoim.friends.filter(id => id !== friendId)
//       }));
//   };

//   const onChange = (event) => {
//     const { value, name } = event.target; //event.target에서 name과 value만 가져오기
//     setMoim({
//       ...moim,
//       [name]: value,
//     });
//   };

//   const onDateChange = date => {
//     setSelectedDate(date);
//     setMoim(prevMoim => ({
//         ...prevMoim,
//         datetime: date
//     }));
//     console.log(date)
//   };

//   const saveMoim = async () => {
//     await axios.post(`//localhost:8081/moim/room`, moim).then((res) => {
//       alert('등록되었습니다.');
//       navigate('/moim');
//     });
//   };

//   const backToList = () => {
//     navigate('/moim');
//   };

//   return (
//     <div>
//       <div>
//         <span>제목</span>
//         <input type="text" name="title" value={title} onChange={onChange} />
//       </div>
//       <br />
//       <div>
//         <label>
//         인원:
//         <select value={selectedNumber} name="number" onChange={(e) => setSelectedNumber(Number(e.target.value))}>
//           <option value="2">2</option>
//           <option value="3">3</option>
//           <option value="4">4</option>
//           <option value="5">5</option>
//           <option value="6">6</option>
//           <option value="7">7</option>
//           <option value="8">8</option>
//         </select>
//       </label>
//       </div>
//       <div>
//       날짜:
//       <Calendar onChange={onDateChange} name="datetime" value={selectedDate} />
//       </div>
//       <br />
//       <div>
//         <span>내용</span>
//         <textarea
//           name="contents"
//           cols="30"
//           rows="10"
//           value={contents}
//           onChange={onChange}
//         ></textarea>
//       </div>
//       <br />
//       <div>
//         <button onClick={saveMoim}>저장</button>
//         <button onClick={backToList}>취소</button>
//       </div>
//     </div>
//   );
// };

// export default MoimWrite;