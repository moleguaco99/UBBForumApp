import firebase from 'firebase/app';
import 'firebase/storage';

const config = {
    apiKey: "AIzaSyAQg9tX04JjzadROhqcFZ1kXMtLB0v-B5E",
    authDomain: "ubbforumapp.firebaseapp.com",
    databaseURL: "https://ubbforumapp.firebaseio.com",
    projectId: "ubbforumapp",
    storageBucket: "ubbforumapp.appspot.com",
    messagingSenderId: "786404730041",
    appId: "1:786404730041:web:bd5ce2b75d604976712768",
    measurementId: "G-10E4D6LMPL"
  };
  firebase.initializeApp(config);

  const storage = firebase.storage();

  export {
      storage, firebase as default
  }