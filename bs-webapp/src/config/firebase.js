// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyDSp1Q-66tYWeg1q1UJYcIKYr7N6a7Sh4c",
  authDomain: "busstationmanagement-1f2e7.firebaseapp.com",
  projectId: "busstationmanagement-1f2e7",
  storageBucket: "busstationmanagement-1f2e7.appspot.com",
  messagingSenderId: "162695041887",
  appId: "1:162695041887:web:2698c966fbf6237e6e1cea",
  measurementId: "G-YMTK9ZR2KV"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);