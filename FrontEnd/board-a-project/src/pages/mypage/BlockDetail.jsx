import styled from "styled-components";

export default function BlockDetail({ blockList }) {
  console.log(blockList);

  const Row = styled.div`
    && {
      width: 100rem;
    }
  `;

  return (
    <Row className="flex flex-col items-center space-y-4 w-full">
      {blockList?.map((e, index) => (
        <div className="flex items-center space-x-4 bg-gray-100 p-6 rounded-lg shadow-sm w-full max-w-md">
          {" "}
          {/* 패딩을 6으로 조정하여 전체적인 크기를 키움 */}
          <img
            className="w-20 h-20 object-cover rounded-full"
            src={`${import.meta.env.VITE_S3_BASE}${e.profileImage}`}
            alt=""
          />
          <div className="flex flex-col flex-grow text-lg">
            <p className="font-medium">{e.nickname}</p>
            <p className="text-sm text-gray-500">{e.id}</p>
          </div>
          <div className="space-y-2">
            <button className="px-2 py-1 text-sm bg-red-500 text-white rounded-lg">
              차단취소
            </button>
            <button className="px-2 py-1 text-sm bg-green-500 text-white rounded-lg">
              초대하기
            </button>
          </div>
        </div>
      ))}
    </Row>
  );
}
