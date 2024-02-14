import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { Button, CardActionArea, CardActions } from '@mui/material';
import boardGame from "../../assets/images/boardGame.png";

export default function MultiActionAreaCard({info}) {
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardActionArea>
        <CardMedia
          component="img"
          height="140"
          image={import.meta.env.VITE_S3_BASE + info.images[0].name}
          alt="green iguana"
          onError={(e) =>e.target.src=boardGame}
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
          {info.cafe.brand}
          </Typography>
          <Typography variant="body2" color="text.secondary">
          {info.content}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary">
          Share
        </Button>
      </CardActions>
    </Card>
  );
}