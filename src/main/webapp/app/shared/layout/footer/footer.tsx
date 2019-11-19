import './footer.scss';

import React from 'react';

import { Col, Row } from 'reactstrap';

const Footer = props => (
  <div className="footer page-content">
    <Row>
      <Col md="12">
          <h6 style={{fontStyle:"italic"}}>©Copyright 2019 <img src="content/images/copyright-logo.png" className="copyright-logo"/> GForce UBBForum</h6>
      </Col>
    </Row>
  </div>
);

export default Footer;
