import React, { Component } from 'react';
import logo from '../assets/logo.svg';
import '../css/App.css';

class DrinkCard extends Component {
  render() {
    return (
        <div className="Card-Container">
          <strong>{this.props.name}</strong>
          <div className="Drink-Card">
            <img width="200px" height="200px" src={this.props.imageUrl}></img>
          </div>
          <div>
            {this.props.description}
          </div>  
        </div>
    );
  }
}

export default DrinkCard;