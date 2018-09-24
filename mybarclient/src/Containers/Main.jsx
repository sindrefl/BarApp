import React, {Component} from 'react';
import '../css/App.css';

import Navbar from '../Components/Navbar'
import DrinkCard from '../Components/DrinkCard'
import RandomDrinkCard from '../Components/RandomDrink'
import NewDrinkForm from '../Components/NewDrinkForm'

import {Route, Switch} from 'react-router-dom'
import CocktailDashboard from './CocktailDashboard';
import CategoryList from './CategoryList';

import MyBarPage from './MyBarPage'

const axios = require('axios');

class Main extends Component {
    constructor(props) {
        super(props);
        this.state = {
            randomDrink: undefined,
            categories: ['Hardcoded Long', 'Hardcoded Short', 'Mock'],
            allDrinks: undefined,
            glassTypes : []
        }
    }

    componentDidMount() {
        axios
            .get('http://localhost:8080/random')
            .then((response) => {
                let drink = response.data;
                this.setState({randomDrink: drink})
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            });
        axios
            .get('http://localhost:8080/categories')
            .then((response) => {
                let categoryList = response.data;
                this.setState({categories: categoryList});
            })
            .catch((error) => {
                console.log(error)
            });
        axios
            .get('http://localhost:8080/allDrinks')
            .then((response) => {
                this.setState({allDrinks: response.data})
                console.log(response.data[0])
            });
        axios.get('http://localhost:8080/glassTypes/10').then((response) => {
            this.setState({glassTypes : response.data})
        }).catch(error => {
            console.log(error)
        });
    }

    render() {
        return (
            <div className="Main">

            <Navbar glassTypes={this.state.glassTypes}/>

            <Switch>
            <Route path="/" exact render= {() => <CocktailDashboard randomDrink={this.state.randomDrink} categories={this.state.categories} allDrinks={this.state.allDrinks} glassTypes={this.state.glassTypes}/>}></Route>
            <Route path="/glass/:name/:id" render={({match}) => <DrinkCard name={match.params.name} ></DrinkCard>} ></Route>
            <Route path="/glass/:name" render={({match}) => <CategoryList match={match} drinks={this.state.allDrinks}></CategoryList>}></Route>
            <Route path="/home/bar" render={() => <MyBarPage></MyBarPage>}></Route>
            </Switch>
            </div>
        );
    }
}


export default Main;