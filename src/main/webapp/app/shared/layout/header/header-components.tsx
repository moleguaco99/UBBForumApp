import React from 'react';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faComments } from '@fortawesome/free-solid-svg-icons';

export const BrandIcon = props => (
  <div {...props} className="brand-icon">
    <img src="content/images/logo-app.png" alt="Logo" />
  </div>
);

export const Brand = props => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <BrandIcon />
    <span style={{ fontStyle: 'italic', color: '#1F305E' }} className="brand-title">
      UBBForumApp
    </span>
  </NavbarBrand>
);
export const Subjects = props => (
  <NavItem>
    <NavLink tag={Link} to="/subjectSearch" style={{ fontStyle: 'italic', color: '#1F305E' }} className="d-flex align-items-center">
      <FontAwesomeIcon icon="book" style={{ fontStyle: 'italic', color: '#1F305E', marginRight: '3px' }} />
      <span style={{ fontStyle: 'italic', color: '#1F305E' }}>Subjects</span>
    </NavLink>
  </NavItem>
);
export const Home = props => (
  <NavItem>
    <NavLink tag={Link} to="/" style={{ fontStyle: 'italic', color: '#1F305E' }} className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" style={{ fontStyle: 'italic', color: '#1F305E', marginRight: '3px' }} />
      <span style={{ fontStyle: 'italic', color: '#1F305E' }}>Home</span>
    </NavLink>
  </NavItem>
);

export const Teacher = props => (
  <NavItem>
    <NavLink tag={Link} to="/teachers" style={{ fontStyle: 'italic', color: '#1F305E' }} className="d-flex align-items-center">
      <FontAwesomeIcon style={{ fontStyle: 'italic', color: '#1F305E', marginRight: '3px' }} icon="book" />
      <span style={{ fontStyle: 'italic', color: '#1F305E' }}>Teachers</span>
    </NavLink>
  </NavItem>
);

export const Forum = props => (
  <NavItem>
    <NavLink tag={Link} to="/forum" style={{ fontStyle: 'italic', color: '#1F305E' }} className="d-flex align-items-center">
      <FontAwesomeIcon icon={faComments} style={{ fontStyle: 'italic', color: '#1F305E', marginRight: '3px' }} />
      <span style={{ fontStyle: 'italic', color: '#1F305E' }}>Forum</span>
    </NavLink>
  </NavItem>
);
