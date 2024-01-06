import React, {ReactNode} from "react";
import {ClassNameMap, makeStyles} from "@mui/styles";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";

const useStyles = makeStyles(styles);

interface ComponentProps{
  children?: ReactNode;
}

export default function Danger(props: ComponentProps) {
  const classes: ClassNameMap<"defaultFontStyle" | "dangerText"> = useStyles();
  const { children    } = props;
  return (
    <div className={classes.defaultFontStyle + " " + classes.dangerText}>
      {children}
    </div>
  );
}
