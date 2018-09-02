import React, { Component } from 'react';
import CocktailDashboard from './Containers/Main';
import './css/App.css';

class App extends Component {

  render() {
    return (
      <div className="App">
        <CocktailDashboard />
      </div>
    );
  }
}

export default App;