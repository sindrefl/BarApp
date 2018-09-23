import React, { Component } from 'react';

class DrinkCard extends Component {


  render() {
    {console.log(this.props)}
    return (
        <div className="Card-Container">
          <strong>{this.props.name}</strong>
          <div className="Drink-Card">
            <img width="200px" height="200px" src={this.props.imageUrl}></img>
          </div>
          <div>
            This is the drink recipe. Served in a <strong>{this.props.glass}</strong> glass.
          </div>

          <div>
            {this.props.description}
          </div>  
        </div>
    );
  }
}

export default DrinkCard;