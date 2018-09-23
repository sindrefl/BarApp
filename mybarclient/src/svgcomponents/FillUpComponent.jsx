import React from 'react';
import JugSvg from './JugSvg';
import VodkaSvg from './VodkaSvg';

import CategoryCard from '../Components/CategoryCard';

const FillUpComponent = (props) => {
    const {type, percent} = props.type;
    console.log(percent);
    console.log(type);
    switch(type) {
        case "Jug":
            return <JugSvg percent={percent}/>
        case "Vodka":
            return <JugSvg percent={percent}/>
        default:
            return <JugSvg percent={percent}/>
    }
}


export default FillUpComponent;8