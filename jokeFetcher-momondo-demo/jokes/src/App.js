import logo from './logo.svg';
import './App.css';
import "./style2.css";
import Home from "./components/Home";
import About from './components/About';
import React, { useEffect, useState } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  NavLink
} from "react-router-dom";




// This site has 3 pages, all of which are rendered
// dynamically in the browser (not server rendered).
//
// Although the page does not ever refresh, notice how
// React Router keeps the URL up to date as you navigate
// through the site. This preserves the browser history,
// making sure things like the back button and bookmarks
// work properly.

export default function BasicExample() {
  const [joke1, setJoke1] = useState("");
  const [joke2, setJoke2] = useState("");
  

  useEffect( () => {
      fetch("http://localhost:8080/jokeFetcher_war_exploded/api/jokes")
      .then(res => res.json())
      .then(data => setJoke1(data.joke1))
  },[])

  useEffect( () => {
    fetch("http://localhost:8080/jokeFetcher_war_exploded/api/jokes")
    .then(res => res.json())
    .then(data => setJoke2(data.joke2))
},[])

  return (
    <Router>
      <div>
        <ul className="header">
          <li>
            <NavLink exact activeClassName="selected" to="/">Home</NavLink>
          </li>
          <li>
            <NavLink exaxt activeClassName="selected" to="/about">About</NavLink>
          </li>
          <li>
            <NavLink exact activeClassName="selected" to="/dashboard">Dashboard</NavLink>
          </li>
        </ul>

        <hr />

        {/*
          A <Switch> looks through all its children <Route>
          elements and renders the first one whose path
          matches the current URL. Use a <Switch> any time
          you have multiple routes, but you want only one
          of them to render at a time
        */}
        <div className="content">
        <Switch>
          <Route exact path="/">
            <Home joke1={joke1} />
          </Route>
          <Route path="/about">
            <About joke2={joke2}/>
          </Route>
          <Route path="/dashboard">
            <Dashboard />
          </Route>
        </Switch>
        </div>
      </div>
    </Router>
  );
}

// You can think of these components as "pages"
// in your app.
 

function Dashboard() {
  return (
    <div>
      <h2>Dashboard</h2>
    </div>
  );
}