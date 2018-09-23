import React, {Component} from 'react';


import FillUpComponent from '../svgcomponents/FillUpComponent';
import RandomDrinkCard from '../Components/RandomDrink';

const axios = require('axios');

class MyBarPage extends Component {
    constructor(props){
        super(props);
        this.state = {
            lastProps : this.props,
            batteri : [{type : "Tequila", percent : 0.6}, {type: "Vodka", percent: 0.2}, {type:"Jug", percent : 0.7,}, {type:"Jug", percent: 0.2}, {type:"Jug", percent: 0.2}]
            
        }
    }


    shouldComponentUpdate(nextProps,nextState){
        return(nextProps === this.state.lastProps);
    }

    render() {
        return <div>
        <div className="flex-horizontal-container">
        {this.state.batteri.map((icon,index) => <div className="flex-horizontal bottleIcon"><FillUpComponent key={index.toString()} type={icon}></FillUpComponent></div>)}
        </div>
        <div className="flex-horizontal-container around">
        <div>
               <RandomDrinkCard ingredients={["hello"]} description={"This will be a user-specific random drink"}></RandomDrinkCard>
        </div>
        <div>
        <RandomDrinkCard ingredients={["hello"]} description={"This could either be some statistics or a suggestion based on previous drinks"}></RandomDrinkCard>
        </div>
             
        </div>
        </div>
    }
}

export default MyBarPage;