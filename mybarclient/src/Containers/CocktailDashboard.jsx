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
                    imageUrl={"http://localhost:8080/images/drinks/Whisky-Sour.jpg"}
                    description={this.props.randomDrink.description}
                    glass={this.props.randomDrink.glass}
                    ingredients={this.props.randomDrink.ingredients}
                    amounts ={this.props.randomDrink.amounts}
                    />
}
                {this.props.categories && <div className="Grid-container">
                    {this
                        .props
                        .categories
                        .map((category, index) => {
                            return <div>
                                <Link to={`/category/${category.name}`}>
                                    <CategoryCard
                                        id={index}
                                        imageUrl={`http://localhost:8080/images/categories/${category.name}.jpg`}
                                        description={category.description}
                                        name={category.name}/>
                                </Link>

                            </div>
                        })}
                </div>}
            </div>
        );
    }
}

export default CocktailDashboard;