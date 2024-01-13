// nodejs library to set properties for components
import PropTypes from "prop-types";
// @material-ui/core components
import Grid from "@material-ui/core/Grid";

import React, {ReactNode} from "react";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";
import {ClassNameMap, makeStyles} from "@mui/styles";
interface componentProps{
  children:ReactNode,
}

const styles = {
  grid: {
    margin: "0 -15px !important",
    width: "unset",
  },
};

const useStyles = makeStyles(styles);

export default function GridContainer(props: componentProps) {
  const classes = useStyles();
  const { children, ...rest } = props;
  return (
    <Grid container {...rest} className={classes.grid}>
      {children}
    </Grid>
  );
}