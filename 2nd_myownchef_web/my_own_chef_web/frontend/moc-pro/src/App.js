import './App.css';
import ShoppingBoard from './components/with_shopping_board/ShoppingBoard';
import Body from './layout/Body';
import Nav from './layout/Nav.jsx';
import './css/Font.css';
import Footer from './layout/Footer.jsx';
import Header from './layout/Header.jsx';


function App() {
  return (
    <div className="App">
      <div className="nev">
        <Nav/>
      </div>
      <div className="main">
        <div className="header">
          <Header/>
        </div>
        <div className="body">
          <Body/>
        </div>
        <div className="footer">
          <Footer/>
        </div>
      </div>
    </div>
  );
}

export default App;