import React, {Component} from 'react';

class RandomDrinkCard extends Component {
    render() {
        return (
            <div className="Random-Card-Container">
                <strong>{this.props.name}</strong>
                <div className="Random-Card">
                    <img width="200px" height="200px" src={this.props.imageUrl} alt={this.props.altUrl}></img>
                    <div className="flex-horizontal-container">
                        <div className="flex-vertical">
                            Served in a <strong>{this.props.glass}</strong> glass.
                            <div className="flex-horizontal-container">
                            <div>
                            <ul>
                            {this.props.ingredients.map(ingredient => <li> {ingredient.name}</li>)
                            }
                            </ul>
                            </div>
                            <div>
                            <ul>
                            {this.props.amounts.map(amount => <li>{amount}</li>)}
                            </ul>
                            </div>
                            </div>
                            {this.props.description}
                            </div>

                        </div>
                        </div>
                </div>
        );
    }
}

export default RandomDrinkCard;