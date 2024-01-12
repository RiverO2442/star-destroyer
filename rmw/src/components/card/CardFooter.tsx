// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/icons

// core components
import React, {ReactNode} from "react";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";
import {ClassNameMap, makeStyles} from "@mui/styles";

interface componentProps {
  className: String,
  plain: boolean,
  profile: boolean,
  stats: boolean,
  chart: boolean,
  children: ReactNode,
}

const useStyles = makeStyles(styles);

export default function CardFooter(props: componentProps) {
  const classes = useStyles();
  const { className, children, plain, profile, stats, chart, ...rest } = props;
  const cardFooterClasses = classNames({
    [classes.cardFooter]: true,
    [classes.cardFooterPlain]: plain,
    [classes.cardFooterProfile]: profile,
    [classes.cardFooterStats]: stats,
    [classes.cardFooterChart]: chart,
    [className]: className !== undefined,
  });
  return (
    <div className={cardFooterClasses} {...rest}>
      {children}
    </div>
  );
}

