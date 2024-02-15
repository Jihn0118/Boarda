import * as React from 'react';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';

function Review({ review }) {
  return (
    <>
      <ListItem alignItems="flex-start">
        <ListItemAvatar>
          <Avatar alt={"C"} src={review.cafe.image} />
        </ListItemAvatar>
        <ListItemText
          primary={review.cafe.branch}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {review.cafe.location}
              </Typography>
              <Typography
                sx={{ display: 'block', textAlign: 'right', fontSize: '11px' }} // 오른쪽 정렬을 위해 textAlign: 'right'를 추가했습니다
                component="span"
                variant="body2"
                color="text.primary"
              >
                {review.cafe.contact}
              </Typography>
            </React.Fragment>
          }
        />
      </ListItem>
      <Divider variant="inset" component="li" />
    </>
  );
}

export default function AlignItemsList({reviewList}) {
  
  const uniqueReviewList = reviewList.reduce((acc, current) => {
    if(!acc.find(review => review.cafe.id === current.cafe.id)) {
      return acc.concat([current]);
    } else {
      return acc;
    }
  }, []);


  return (
    <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
      {uniqueReviewList.map((review, index) => (
        <Review
          key={index}
          review={review}
        />
      ))}
    </List>
  );
}