import { Button, Card, CardActionArea, CardActions, CardContent, CardMedia, Grid, Typography } from "@mui/material";
import { PageInfo } from "../../components/menu-bar/PageInfo";
import { pages } from "../Pages";

type PageCardProps = {
  pageInfo: PageInfo
}

export const PageCard = (props: PageCardProps) => {
  const pageInfo = props.pageInfo;
  return <>
    <Card>
      <CardActionArea href={`#/${pageInfo.path}`}>
        <CardMedia
          component="img"
          height="150"
          image={pageInfo.image}
          sx={{ p: 3, objectFit: "contain" }}
        />
        <CardContent sx={{ height: 150 }}>
          <Typography gutterBottom variant="h5" component="div">
            {pageInfo.title}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            {pageInfo.description}
          </Typography>
        </CardContent>
      </CardActionArea>
    </Card>
  </>
}

export const MainPage = () => {
  return (
    <>
      <Grid container spacing={3}>
        <Grid item xs={2}>
          <PageCard pageInfo={pages[1]} />
        </Grid>
        <Grid item xs={2}>
          <PageCard pageInfo={pages[2]} />
        </Grid>
      </Grid>
    </>
  );
}
