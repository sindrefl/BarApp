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
                    imageUrl={`http://localhost:8080/images/drinks/${this
                    .props
                    .randomDrink
                    .name
                    .replace(' ', '_')}.jpg`}
                    altUrl
                    ={this.props.randomDrink.imageUrl}
                    description={this.props.randomDrink.description}
                    glass={this.props.randomDrink.glass}
                    ingredients={this.props.randomDrink.ingredients}
                    amounts
                    ={this.props.randomDrink.amounts}/>
}
                {this.props.glassTypes && <div className="Grid-container">
                    <div className="Grid-header">
                        <div>
                            <h1>Find drinks by glass type</h1>
                        </div>
                    </div>
                    <div className="Grid">

                        {this
                            .props
                            .glassTypes
                            .map((glass, index) => {
                                return <div>
                                    <Link key={glass} to={`/filtered/glass=${glass}`}>
                                        <CategoryCard
                                            imageUrl={`http://localhost:8080/images/glass/${glass}.jpg`}
                                            name={glass}/>
                                    </Link>

                                </div>
                            })}
                    </div>
                </div>
}

{this.props.categories[2] && console.log(this.props.categories[2].name.replace(/[/"]/g, "").replace(/ /g,"_"))}
                {this.props.glassTypes && <div className="Grid-container">
                    <div className="Grid-header">
                        <div>
                            <h1>Find drinks by category</h1>
                        </div>
                    </div>
                    <div className="Grid">
                        {this
                            .props
                            .categories
                            .map((cat, index) => {
                                return <div>
                                    <Link key={index} to={`/filtered/category=${cat.name}`}>
                                        <CategoryCard
                                            imageUrl={`http://localhost:8080/images/categories/${cat.name.replace(/[/"]/g, "").replace(/ /g,"_")}.jpg`}
                                            name={cat.name}/>
                                    </Link>

                                </div>
                            })}
                    </div>
                </div>
}

            </div>
        );
    }
}

export default CocktailDashboard;