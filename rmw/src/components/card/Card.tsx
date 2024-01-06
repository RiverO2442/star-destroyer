import React, { FC, ReactNode } from "react";
import {makeStyles} from "@mui/styles";
import cardStyle from "../../assets/jss/material-dashboard-react/components/card-style";
// import classNames from "classnames";
// import PropTypes from "prop-types";
// import { makeStyles } from "@material-ui/core/styles";

interface CardProps {
    className?: string;
    plain?: boolean;
    profile?: boolean;
    chart?: boolean;
    children?: ReactNode;
}

const useStyles = makeStyles(cardStyle);

const Card: FC<CardProps> = (props) => {
    const classes = useStyles();
    const { className, children, plain, profile, chart, ...rest } = props;
    const cardClasses = classNames({
        [classes.card]: true,
        [classes.cardPlain]: plain,
        [classes.cardProfile]: profile,
        [classes.cardChart]: chart,
        [className]: className !== undefined,
    });
    return (
        <div className={cardClasses} {...rest}>
            {children}
        </div>
    );
}

Card.propTypes = {
    className: PropTypes.string,
    plain: PropTypes.bool,
    profile: PropTypes.bool,
    chart: PropTypes.bool,
    children: PropTypes.node,
};

export default Card;