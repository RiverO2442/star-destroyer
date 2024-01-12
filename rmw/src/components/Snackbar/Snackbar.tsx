import classNames from "classnames";
import PropTypes from "prop-types";
// @material-ui/core components
import Snack from "@material-ui/core/Snackbar";
import IconButton from "@material-ui/core/IconButton";
// @material-ui/icons
import Close from "@material-ui/icons/Close";
// core components
import React, {ReactNode} from "react";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";
import {ClassNameMap, makeStyles} from "@mui/styles";
const useStyles = makeStyles(styles);

interface componentProps {
  message: ReactNode,
  color: "info"| "success"| "warning"| "danger"| "primary",
  close: boolean,
  icon: object,
  place: "tl"| "tr"| "tc"| "br"| "bl"| "bc",
  open: boolean,
  rtlActive: boolean,
  closeNotification: Function,
}

export default function Snackbar(props: componentProps) {
  const classes = useStyles();
  const { message, color, close, icon, place, open, rtlActive } = props;
  var action: ReactNode[] = [];
  const messageClasses = classNames({
    [classes.iconMessage]: icon !== undefined,
  });
  if (close !== undefined) {
    action = [
      <IconButton
        className={classes.iconButton}
        key="close"
        aria-label="Close"
        color="inherit"
        onClick={() => props.closeNotification()}
      >
        <Close className={classes.close} />
      </IconButton>,
    ];
  }
  return (
    <Snack
      anchorOrigin={{
        vertical: place.indexOf("t") === -1 ? "bottom" : "top",
        horizontal:
          place.indexOf("l") !== -1
            ? "left"
            : place.indexOf("c") !== -1
            ? "center"
            : "right",
      }}
      open={open}
      message={
        <div>
          {icon !== undefined ? <props.icon className={classes.icon} /> : null}
          <span className={messageClasses}>{message}</span>
        </div>
      }
      action={action}
      ContentProps={{
        classes: {
          root: classes.root + " " + classes[color],
          message: classes.message,
          action: classNames({ [classes.actionRTL]: rtlActive }),
        },
      }}
    />
  );
}