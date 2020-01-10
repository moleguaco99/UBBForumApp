import './home.scss';

import React from 'react';
import { Link } from 'react-router-dom';

import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';

export type IHomeProp = StateProps;

export const Home = (props: IHomeProp) => {
  const { account } = props;

  return (
    <Row>
      <Col md="9">
        <h2 style={{marginTop: "3%", fontWeight: "bold", color:"#1F305E", fontStyle: "italic", textShadow: "2px 2px #DCDCDC"}}>Welcome to our forum!</h2>
        {account && account.login ? (
          <div>
            <Alert style={{backgroundColor: "#1F305E"}}>You are logged in as user {account.login}.</Alert>
          </div>
        ) : (
          <div>
            <Alert style={{backgroundColor: "#1F305E", color:"#E0FBFC"}}>
              <Link to="/login" style={{color:"#E0FBFC"}} className="alert-link">
                {' '}
                Sign In
              </Link>
            </Alert>
            <Alert style={{backgroundColor: "#1F305E", color: "#E0FBFC"}}>
              You do not have an account yet?&nbsp;
              <Link to="/account/register" style={{color: "#E0FBFC"}} className="alert-link">
                Register a new account
              </Link>
            </Alert>
          </div>
        )}
      </Col>
      <Col md="3" className="pad">
        <span className="hipster rounded" />
      </Col>
    </Row>
  );
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(Home);
