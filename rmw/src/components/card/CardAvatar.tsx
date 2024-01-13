// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/icons
// core components

import React, {ReactNode} from "react";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";
import {ClassNameMap, makeStyles} from "@mui/styles";

interface componentProps {
  children: ReactNode,
  className: String,
  profile: boolean,
  plain: boolean,
}

const useStyles = makeStyles(styles);

export default function CardAvatar(props: componentProps) {
  const classes = useStyles();
  const { children, className, plain, profile, ...rest } = props;
  const cardAvatarClasses = classNames({
    [classes.cardAvatar]: true,
    [classes.cardAvatarProfile]: profile,
    [classes.cardAvatarPlain]: plain,
    [className]: className !== undefined,
  });
  return (
    <div className={cardAvatarClasses} {...rest}>
      {children}
    </div>
  );
}
