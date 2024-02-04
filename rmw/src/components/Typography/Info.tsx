import React, {ReactNode} from "react";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";
import {ClassNameMap, makeStyles} from "@mui/styles";

const useStyles = makeStyles(styles);

interface ComponentProps{
  children?: ReactNode;
}

export default function Info(props: ComponentProps) {
  const classes: ClassNameMap<"defaultFontStyle" | "infoText"> = useStyles();
  const { children } = props;
  return (
    <div className={classes.defaultFontStyle + " " + classes.infoText}>
      {children}
    </div>
  );
}
