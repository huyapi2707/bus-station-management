import '../styles/loading.css';
import {LoadingContext} from '../config/context';
import {useContext} from 'react';
const Loading = () => {
  const {loading} = useContext(LoadingContext);
  return (
    <div style={{display: loading}} className="loadingScreen">
      <div className="loader"></div>
      <div className="loader-text">Loading...</div>
    </div>
  );
};

export default Loading;
