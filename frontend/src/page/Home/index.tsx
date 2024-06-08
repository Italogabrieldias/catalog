import { ReactComponent as MainImagen } from "assets/images/main-image.svg";
import Navbar from "components/Navbar";
import "./styles.css";
import ButtonIcon from "components/buttonIcon";

const Home = () => {
  return (
    <>
      <Navbar />
      <div className="home-container">
        <div className="base-card home-card">
          <div className="home-content-container">
            <div>
              <h1>Conheça o melhro catálago de produtos</h1>
              <p>
                Ajudaremos você a encontrar os melhores produtos disponíveis no
                mercado.
              </p>
            </div>
            <ButtonIcon />
          </div>
          <div className="home-image-container">
            <MainImagen />
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;
