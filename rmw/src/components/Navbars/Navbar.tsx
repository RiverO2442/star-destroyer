import classNames from "classnames";
import PropTypes from "prop-types";
// @material-ui/core components
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import IconButton from "@material-ui/core/IconButton";
import Hidden from "@material-ui/core/Hidden";
// @material-ui/icons
import Menu from "@material-ui/icons/Menu";
// core components
// import AdminNavbarLinks from "./AdminNavbarLinks.js";
// import RTLNavbarLinks from "./RTLNavbarLinks.tsx";
import Button from "../CustomButtons/Button";

//hooks
// import { useRouteName } from "hooks";

import React, {ReactNode} from "react";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";
import {ClassNameMap, makeStyles} from "@mui/styles";
const useStyles = makeStyles(styles);

interface componentProps{
  color: "primary"| "info"| "success"| "warning"| "danger",
  rtlActive: boolean,
  handleDrawerToggle: Function,
  routes: object[],
}

export default function Header(props: componentProps) {
  const classes: any = useStyles();
  // const routeName = useRouteName();
  const { color } = props;
  const appBarClasses: string = classNames({
    [" " + classes[color]]: color,
  });
  return (
    <AppBar className={classes.appBar + appBarClasses}>
      <Toolbar className={classes.container}>
        <div className={classes.flex}>
          {/* Here we create navbar brand, based on route name */}
          <Button color="transparent" className={classes.title}>
            {/*{routeName}*/}
          </Button>
        </div>
        {/*<Hidden smDown implementation="css">*/}
        {/*  {props.rtlActive ? <RTLNavbarLinks /> : <AdminNavbarLinks />}*/}
        {/*</Hidden>*/}
        {/*<Hidden mdUp implementation="css">*/}
        {/*  <IconButton*/}
        {/*    color="inherit"*/}
        {/*    aria-label="open drawer"*/}
        {/*    onClick={props.handleDrawerToggle}*/}
        {/*  >*/}
        {/*    <Menu />*/}
        {/*  </IconButton>*/}
        {/*</Hidden>*/}
      </Toolbar>
    </AppBar>
  );
}