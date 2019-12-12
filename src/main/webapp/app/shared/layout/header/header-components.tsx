import React from 'react';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';


export const BrandIcon = props => (
  <div {...props} className="brand-icon">
     <img src="content/images/logo-app.png" alt="Logo" />
  </div>
);

export const Brand = props => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <BrandIcon />
    <span style={{fontStyle: "italic", color:"#1F305E"}} className="brand-title">UBBForumApp</span>
  </NavbarBrand>
);

export const Home = props => (
  <NavItem>
    <NavLink tag={Link} to="/" style={{fontStyle: "italic", color:"#1F305E"}} className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" style={{fontStyle: "italic", color:"#1F305E"}}/>
      <span style={{fontStyle: "italic", color:"#1F305E"}}>Home</span>
    </NavLink>
  </NavItem>
);

export const Teacher = props => (
  <NavItem>
    <NavLink tag={Link} to="/teachers" style={{fontStyle: "italic", color:"#1F305E"}} className="d-flex align-items-center">
        <FontAwesomeIcon style={{fontStyle: "italic", color:"#1F305E"}} icon="book" />
        <span style={{fontStyle: "italic", color:"#1F305E"}}>Teachers</span>
    </NavLink>
  </NavItem>
);
