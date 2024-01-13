import React, {ReactNode} from "react";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";
import {ClassNameMap, makeStyles} from "@mui/styles";

const useStyles = makeStyles(styles);

interface componentProps{
  children: ReactNode,
}
export default function Muted(props: componentProps) {
  const classes = useStyles();
  const { children } = props;
  return (
    <div className={classes.defaultFontStyle + " " + classes.mutedText}>
      {children}
    </div>
  );
}
