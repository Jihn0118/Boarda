import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Modal from "react-modal";
import { saveMoim } from "../../api/moimAPI";
import "react-calendar/dist/Calendar.css";
import moment from "moment";
import { useRecoilState } from "recoil";
import { moimState } from "../../recoil/atoms/moimState";
import { locationState } from "../../recoil/atoms/moimState";
import { styled } from '@mui/material/styles';
import Tooltip from '@mui/material/Tooltip';
import Stack from '@mui/material/Stack';
import { DemoContainer, DemoItem } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { TimePicker } from '@mui/x-date-pickers/TimePicker';
import dayjs from "dayjs";

const ProSpan = styled('span')({
  display: 'inline-block',
  height: '1em',
  width: '1em',
  verticalAlign: 'middle',
  marginLeft: '0.3em',
  marginBottom: '0.08em',
  backgroundSize: 'contain',
  backgroundRepeat: 'no-repeat',
  backgroundImage: 'url(https://mui.com/static/x/pro.svg)',
});

function Label({ componentName, valueType, isProOnly }) {
  const content = (
    <span>
      <strong>{componentName}</strong> for {valueType} editing
    </span>
  );

  if (isProOnly) {
    return (
      <Stack direction="row" spacing={0.5} component="span">
        <Tooltip title="Included on Pro package">
          <a
            href="https://mui.com/x/introduction/licensing/#pro-plan"
            aria-label="Included on Pro package"
          >
            <ProSpan />
          </a>
        </Tooltip>
        {content}
      </Stack>
    );
  }

  return content;
}

const MoimMakeModal = ({ isOpen, onRequestClose }) => {
  const navigate = useNavigate();
  const [moim, setMoim] = useRecoilState(moimState);
  const [location] = useRecoilState(locationState);
  const [date, setDate] = useState(dayjs());
  const [time, setTime] = useState(dayjs().hour(0).minute(0));

  const onDateChange = (newDate) => {
    setDate(newDate);
    setMoim((prevMoim) => ({
      ...prevMoim,
      datetime:
        moment(newDate.toDate()).format("YYYY-MM-DD") +
        "T" +
        moment(time.toDate()).format("HH:mm:ss"),
    }));
  };
  
  const onTimeChange = (newTime) => {
    setTime(newTime);
    setMoim((prevMoim) => ({
      ...prevMoim,
      datetime:
        moment(date.toDate()).format("YYYY-MM-DD") +
        "T" +
        moment(newTime.toDate()).format("HH:mm:ss"),
    }));
  };

  const onChange = (event) => {
    const { name, value } = event.target;
    setMoim((prevMoim) => ({
      ...prevMoim,
      [name]: name === "number" ? Number(value) : value,
    }));
  };

  const saveMoimData = async () => {
    console.log(moim);
    try {
      await saveMoim(moim);
      alert("등록되었습니다.");
      navigate("/moim/list", { state: { updated: true } });
      onRequestClose();
    } catch (error) {
      console.error("모임 저장 중 에러가 발생했습니다:", error);
    }
  };

  const backToList = () => {
    navigate("/moim/list");
    onRequestClose();
  };

  useEffect(() => {
    setMoim((prevMoim) => ({
      ...prevMoim,
      location,
    }));
  }, [location, setMoim]);

  useEffect(() => {
    console.log(date);
    console.log(time);
    setMoim((prevMoim) => ({
      ...prevMoim,
      datetime:
        moment(date.toDate()).format("YYYY-MM-DD") +
        "T" +
        moment(time.toDate()).format("HH:mm:ss"),
    }));
    console.log(moim);
  }, [time, date]);

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <Modal
        isOpen={isOpen}
        onRequestClose={onRequestClose}
        className="fixed inset-0 flex items-center justify-center z-50"
      >
        <div style={{width:"600px"}} className="bg-white p-6 rounded-lg shadow-xl w-2/4 h-3/4 flex flex-col items-center justify-center space-y-4">

          <div className="flex space-x-2 w-full">
            <span className="font-bold text-lg">제목</span>
            <input
              type="text"
              name="title"
              value={moim.title}
              onChange={onChange}
              className="border rounded py-2 px-4"
              style={{width:"500px"}}
            />
          </div>
          <div className="flex items-center space-x-2 w-full">
            <label className="font-bold text-lg">
              인원
              <select
                value={moim.number}
                name="number"
                onChange={onChange}
                className="ml-2 border rounded py-1"
              >
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
          <div className="flex items-center w-full font-bold text-lg ">
            내용 
            <textarea
              name="content"
              cols="60"
              rows="3"
              value={moim.content}
              onChange={onChange}
            ></textarea>
          </div>
          <div className="flex items-center w-full">
          <DemoContainer
            components={[
              'DatePicker',
              'TimePicker',
            ]}
          >
            <DemoItem label={<Label componentName="DatePicker" valueType="date"/>}>
              <DatePicker value={date} onChange={onDateChange} />
            </DemoItem>
            <DemoItem label={<Label componentName="TimePicker" valueType="time" />}>
              <TimePicker value={time} onChange={onTimeChange} />
            </DemoItem>
          </DemoContainer>
          </div>
        
          <div className="flex items-center space-x-2 justify-center w-full">
            <button
              onClick={saveMoimData}
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            >
              저장
            </button>
            <button
              onClick={backToList}
              className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded"
            >
              취소
            </button>
          </div>
        </div>
      </Modal>
    </LocalizationProvider>
  );
};

export default MoimMakeModal;
