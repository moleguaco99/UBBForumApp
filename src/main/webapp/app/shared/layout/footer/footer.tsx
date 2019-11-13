import './footer.scss';

import React from 'react';

import { Col, Row } from 'reactstrap';

const Footer = props => (
  <div className="footer page-content">
    <Row>
      <Col md="12">
        <h6>©Copyright 2019 GForce UBBForum</h6>
      </Col>
    </Row>
  </div>
);

export default Footer;
