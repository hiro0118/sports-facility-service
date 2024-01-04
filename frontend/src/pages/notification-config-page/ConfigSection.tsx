import { Box, Divider, Typography } from "@mui/material";

export type ConfigSectionProps = {
  title: string;
  children: any
}

export const ConfigSection = (props: ConfigSectionProps) => {
  return (
    <>
      <Box sx={{ my: 1 }}>
        <Typography variant="h5" sx={{ mx: 1 }}>
          {props.title}
        </Typography>
        <Divider />
        {props.children}
      </Box>
    </>
  );
}
