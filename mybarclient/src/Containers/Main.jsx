import React, {Component} from 'react';
import logo from '../assets/logo.svg';
import '../css/App.css';

import Navbar from '../Components/Navbar'
import DrinkCard from '../Components/DrinkCard'
import RandomDrinkCard from '../Components/RandomDrink'

const axios = require('axios');

class CocktailDashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            randomDrink: undefined
            ,
            categories: [
                {
                    name: "Category 1",
                    picture: require("../assets/ThePlaceholder.jpg"),
                    description: "category description"
                }, {
                    name: "Category 1",
                    picture: require("../assets/ThePlaceholder.jpg"),
                    description: "category description"
                }, {
                    name: "Category 1",
                    picture: require("../assets/ThePlaceholder.jpg"),
                    description: "category description"
                }, {
                    name: "Category 1",
                    picture: require("../assets/ThePlaceholder.jpg"),
                    description: "category description"
                }, {
                    name: "Category 1",
                    picture: require("../assets/ThePlaceholder.jpg"),
                    description: "category description"
                }
            ]
        }
    }

    componentDidMount() {
        axios
            .get('http://localhost:8080/random')
            .then((response) => {
                console.log(response)
                let drink = response.data;
                this.setState({randomDrink: drink})
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });
    }

    render() {
        {console.log(this.state)}

        return (
            
            <div className="Main">
                <Navbar/>
                {this.state.randomDrink && 
                <RandomDrinkCard
                    name={this.state.randomDrink.name}
                    imageUrl={require("../assets/ThePlaceholder.jpg")}
                    description={this.state.randomDrink.description}
                    glass={this.state.randomDrink.glass}
                    ingredients={this.state.randomDrink.ingredients} />
                }
                <div className="Grid-container">
                    {this
                        .state
                        .categories
                        .map((category, index) => <DrinkCard
                            id={index}
                            imageUrl={category.picture}
                            description={category.description}
                            name={category.name}/>)}
                </div>

            </div>
        );
    }
}

export default CocktailDashboard;