import React, {Component} from 'react';
import logo from '../assets/logo.svg';
import '../css/App.css';

import DrinkCard from '../Components/DrinkCard'
import RandomDrinkCard from '../Components/RandomDrink'

import {Link, Route} from 'react-router-dom'
import CategoryCard from '../Components/CategoryCard';

const axios = require('axios');

class CocktailDashboard extends Component {
    render() {
        return (
            <div className="Main">
                 {this.props.randomDrink && <RandomDrinkCard
                    name={this.props.randomDrink.name}
                    imageUrl={`http://localhost:8080/images/drinks/${this.props.randomDrink.name.replace(' ','_')}.jpg`}
                    altUrl ={this.props.randomDrink.imageUrl}
                    description={this.props.randomDrink.description}
                    glass={this.props.randomDrink.glass}
                    ingredients={this.props.randomDrink.ingredients}
                    amounts ={this.props.randomDrink.amounts}
                    />
}
                {this.props.glassTypes && <div className="Grid-container">
                    {this
                        .props
                        .glassTypes
                        .map((glass, index) => {
                            return <div>
                                <Link to={`/glass/${glass}`}>
                                    <CategoryCard
                                        id={index}
                                        imageUrl={`http://localhost:8080/images/glass/${glass}.jpg`}
                                        name={glass}/>
                                </Link>

                            </div>
                        })}
                        </div>
                }
            </div>
        );
    }
}

export default CocktailDashboard;