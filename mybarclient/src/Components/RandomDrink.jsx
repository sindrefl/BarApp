import React, {Component} from 'react';
import logo from '../assets/logo.svg';
import '../css/App.css';

class RandomDrinkCard extends Component {
    render() {
        return (
            <div className="Random-Card-Container">
                <strong>{this.props.name}</strong>
                <div className="Drink-Card">
                    <img width="200px" height="200px" src={this.props.imageUrl}></img>
                    <div className="Descriptions">
                        <div>
                            <strong>{this.props.glass}</strong>
                            <ul>
                            {this.props.ingredients.map(element => <li> {element.amount} : {element.name}</li>)
                            }
                            </ul>
                        </div>
                        <div>
                            {this.props.description}

                        </div>

                    </div>
                </div>
            </div>
        );
    }
}

export default RandomDrinkCard;