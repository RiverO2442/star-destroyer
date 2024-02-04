// nodejs library that concatenates classes
import classNames from "classnames";
// material-ui components
import Button from "@material-ui/core/Button";

import React, {ReactNode} from "react";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";
import {ClassNameMap, makeStyles} from "@mui/styles";

interface componentProps {
  color?: "primary"|
  "info"|
  "success"|
  "warning"|
  "danger"|
  "rose"|
  "white"|
  "transparent",
  size?: "sm"| "lg",
  simple?: boolean,
  round?: boolean,
  disabled?: boolean,
  block?: boolean,
  link?: boolean,
  justIcon?: boolean,
  className?: String,
  // use this to pass the classes props from Material-UI
  muiClasses?: object,
  children?: ReactNode,
}

const useStyles = makeStyles(styles);

export default function RegularButton(props: componentProps) {
  const classes = useStyles();
  const {
    color,
    round,
    children,
    disabled,
    simple,
    size,
    block,
    link,
    justIcon,
    className,
    muiClasses,
    ...rest
  } = props;
  const btnClasses = classNames({
    [classes.button]: true,
    [classes[size]]: size,
    [classes[color]]: color,
    [classes.round]: round,
    [classes.disabled]: disabled,
    [classes.simple]: simple,
    [classes.block]: block,
    [classes.link]: link,
    [classes.justIcon]: justIcon,
    [className]: className,
  });
  return (
    <Button {...rest} classes={muiClasses} className={btnClasses}>
      {children}
    </Button>
  );
}

