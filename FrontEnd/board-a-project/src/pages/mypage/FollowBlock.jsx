import React, { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import myPageAPI from "../../api/mypageAPI";
import { loginUserState } from "../../recoil/atoms/userState";
import FollowDetail from "./FollowDetail";
import BlockDetail from "./BlockDetail";
import { Tab, Tabs } from "@mui/material";

export default function FollowBlock() {
  const loginUser = useRecoilValue(loginUserState);
  const [follow, setFollow] = useState([]);
  const [followList, setFollowList] = useState([]);
  const [blockList, setBlockList] = useState([]);

  // 백에서 로그인 id 기준 팔로우 목록 받아와서 뿌리기
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await myPageAPI.getFollowList();
        console.log(res);
        console.log(res.data);
        setFollow(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, [loginUser.id]);

  useEffect(() => {
    if (!!follow?.length !== 0) {
      setFollowList(follow.filter((e) => e.flag === "F"));
      setBlockList(follow.filter((e) => e.flag !== "F"));
    }
  }, [follow]);
  // Flag에 따라 차단/팔로우 디테일로 props 내려줄 것

  function TabMenu({ followList, blockList }) {
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
          <Tab label="팔로우 목록" />
          <Tab label="차단 목록" />
        </Tabs>
        {selectedTab === 0 ? (
          <FollowDetail followList={followList} />
        ) : (
          <BlockDetail blockList={blockList} />
        )}
      </div>
    );
  }

  return (
    <div>
      <TabMenu followList={followList} blockList={blockList}></TabMenu>
    </div>
  );
}
