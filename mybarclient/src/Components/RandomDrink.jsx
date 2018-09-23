import React, {Component} from 'react';

class RandomDrinkCard extends Component {
    render() {
        let list = ["One", "Two", "Three", "Four"]
        return (
            <div className="Random-Card-Container">


                <strong>{this.props.name}</strong>
                <ul>
                {list.map((item) => <li className="horizontal-list">item</li>)}
                </ul>
                <div className="Random-Card">
                    <img width="200px" height="200px" src={this.props.imageUrl} alt="Random drink not found"></img>
                    <div>
                        <div className="flex-horizontal">
                            <strong>{this.props.glass}</strong>
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
                        </div>
                            {this.props.description}

                        </div>
                </div>
        );
    }
}

export default RandomDrinkCard;