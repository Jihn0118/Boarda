import React, { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import myPageAPI from "../../api/mypageAPI";
import { loginUserState } from "../../recoil/atoms/userState";
import FollowDetail from "./FollowDetail";
import BlockDetail from "./BlockDetail";
import { Tab, Tabs } from "@mui/material";


export default function FollowBlock() {
  const loginUser = useRecoilValue(loginUserState);
  const [follow, setFollow] = useState({});

  // 백에서 로그인 id 기준 팔로우 목록 받아와서 뿌리기
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await myPageAPI.getFollowList();
        console.log(res);
        console.log(res.data);
        setFollow(res.data);
        let followList = [];
        let blockList = [];
        if (!!follow?.length !== 0) {
          followList = follow.filter((e) => e.flag === "F");
          blockList = follow.filter((e) => e.flag !== "F");
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, [loginUser.id]);
  // Flag에 따라 차단/팔로우 디테일로 props 내려줄 것

  function TabMenu() {
    const [selectedTab, setSelectedTab] = useState(0);

    const handleChange = (event, newValue) => {
      setSelectedTab(newValue);
    };

    return (
      <div className="p-10">
        <Tabs
          value={selectedTab}
          onChange={handleChange}
          indicatorColor="primary"
          textColor="primary"
          centered
        >
          <Tab label="차단 목록" />
          <Tab label="팔로우 목록" />
        </Tabs>
        {selectedTab === 0 ? <FollowDetail /> : <BlockDetail />}
      </div>
    );
  }

  return <div>
    <TabMenu></TabMenu>
  </div>;
}
