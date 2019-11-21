import React from 'react';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import appConfig from 'app/config/constants';

export const BrandIcon = props => (
  <div {...props} className="brand-icon">
    {/*<img src="content/images/logo-jhipster.png" alt="Logo" />*/}
    <h4 style={{border:"1px solid"}}>Ilarion Logo   </h4>
  </div>
);

export const Brand = props => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <BrandIcon />
    <span className="brand-title">UBBForumApp</span>
  </NavbarBrand>
);

export const Home = props => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span>Home</span>
    </NavLink>
  </NavItem>
);

export const Teacher = props => (
  <NavItem>
    <NavLink tag={Link} to="/teachers" className="d-flex align-items-center">
        <FontAwesomeIcon icon="book" />
        <span>Teachers</span>
    </NavLink>
  </NavItem>
);
