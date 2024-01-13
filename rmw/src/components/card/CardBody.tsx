// nodejs library that concatenates classes
import classNames from "classnames";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// @material-ui/icons

// core components
import React, {ReactNode} from "react";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";
import {ClassNameMap, makeStyles} from "@mui/styles";

interface componentProps {
  className: String,
  plain: boolean,
  profile: boolean,
  children: ReactNode,
}

const useStyles = makeStyles(styles);

export default function CardBody(props: componentProps) {
  const classes = useStyles();
  const { className, children, plain, profile, ...rest } = props;
  const cardBodyClasses = classNames({
    [classes.cardBody]: true,
    [classes.cardBodyPlain]: plain,
    [classes.cardBodyProfile]: profile,
    [className]: className !== undefined,
  });
  return (
    <div className={cardBodyClasses} {...rest}>
      {children}
    </div>
  );
}
