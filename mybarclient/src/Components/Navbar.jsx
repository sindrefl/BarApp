import React, { Component } from 'react';
import logo from '../assets/logo.svg';
import '../css/App.css';

class Main extends Component {
  render() {
    return (
        <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h1 className="App-title">Welcome to React</h1>
      </header>
    );
  }
}

export default Main;