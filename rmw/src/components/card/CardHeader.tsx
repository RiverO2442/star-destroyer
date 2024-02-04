// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/icons

// core components
import React, {ReactNode} from "react";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";
import {ClassNameMap, makeStyles} from "@mui/styles";

interface componentProps {
  className: String,
  color: "warning"|
  "success"|
  "danger"|
  "info"|
  "primary"|
  "rose",
  plain: boolean,
  stats: boolean,
  icon: boolean,
  children: ReactNode,
}

const useStyles = makeStyles(styles);

export default function CardHeader(props: componentProps) {
  const classes = useStyles();
  const { className, children, color, plain, stats, icon, ...rest } = props;
  const cardHeaderClasses = classNames({
    [classes.cardHeader]: true,
    [classes[color + "CardHeader"]]: color,
    [classes.cardHeaderPlain]: plain,
    [classes.cardHeaderStats]: stats,
    [classes.cardHeaderIcon]: icon,
    [className]: className !== undefined,
  });
  return (
    <div className={cardHeaderClasses} {...rest}>
      {children}
    </div>
  );
}

