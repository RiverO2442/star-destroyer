// nodejs library that concatenates classes
import classNames from "classnames";
// core components
import React, {ReactNode} from "react";
import styles from "../../assets/jss/material-dashboard-react/components/typographyStyle.js";
import {ClassNameMap, makeStyles} from "@mui/styles";

interface componentProps {
    className: String,
    color: "warning" |
        "success" |
        "danger" |
        "info" |
        "primary" |
        "rose",
    children: ReactNode,
}

const useStyles = makeStyles(styles);

export default function CardIcon(props) {
    const classes = useStyles();
    const {className, children, color, ...rest} = props;
    const cardIconClasses = classNames({
        [classes.cardIcon]: true,
        [classes[color + "CardHeader"]]: color,
        [className]: className !== undefined,
    });
    return (
        <div className={cardIconClasses} {...rest}>
            {children}
        </div>
    );
}
