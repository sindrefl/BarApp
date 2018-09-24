import React, { Component } from 'react';
import { Route, Link } from 'react-router-dom';
import DrinkCard from '../Components/DrinkCard';


const CategoryList = (props) => {
    return <div>
        This is a category list of {props.match.params.name}
        <div className="Grid-container">
        {props.drinks &&
        props.drinks.filter((drink) => drink.glass === props.match.params.name).map((drink,index) => <Link to={`${props.match.url}/${drink.name}`}><DrinkCard key={index} imageUrl={`http://localhost:8080/images/drinks/${drink.name.replace('\ ','_')}.jpg`} altUrl={drink.imageUrl} name={drink.name} glass={drink.glass} recipe={drink.recipe} ingredients={drink.ingredients} amounts ={drink.amounts} /></Link>)
        }
    </div>
    </div>
}

export default CategoryList;