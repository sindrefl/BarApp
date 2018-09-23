import React, { Component } from 'react';
import { Route, Link } from 'react-router-dom';
import DrinkCard from '../Components/DrinkCard';


const CategoryList = (props) => {
    return <div>
        This is a category list of {props.match.params.name}
        {props.drinks &&
        props.drinks.filter((drink) => drink.glass === props.match.params.name).map((drink) => <Link to={`${props.match.url}/${drink.name}`}><DrinkCard imageUrl={"http://localhost:8080/images/drinks/ThePlaceholder.jpg"} name={drink.name} glass={drink.glass} /></Link>)
        }
    
    </div>
}

export default CategoryList;