import './styles.css';

const Banner = () => {
  return (
    <div
      id="carouselExampleControls"
      className="carousel slide py-5"
      data-bs-ride="carousel"
    >
      <div className="carousel-inner">
        <div className="carousel-item active">
          <img
            src="/images/carousel/c1.jpg"
            className="d-block w-100 rounded"
            alt="banner"
          />
        </div>
        <div className="carousel-item">
          <img
            src="/images/carousel/c2.jpg"
            className="d-block w-100 rounded"
            alt="banner"
          />
        </div>
        <div className="carousel-item">
          <img
            src="/images/carousel/c3.jpg"
            className="d-block w-100 rounded"
            alt="banner"
          />
        </div>
        <div className="carousel-item">
          <img
            src="/images/carousel/c4.jpg"
            className="d-block w-100 rounded"
            alt="banner"
          />
        </div>
      </div>
      <button
        className="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleControls"
        data-bs-slide="prev"
      >
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button
        className="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleControls"
        data-bs-slide="next"
      >
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
      <div>
        <p className="d-flex justify-content-center align-item-center">
          OU BUS
        </p>
      </div>
    </div>
  );
};

export default Banner;
