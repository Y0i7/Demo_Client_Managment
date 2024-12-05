import './App.css';
import ListClientComponent from './components/ListClientComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ClientFormComponent from './components/ClientFormComponent';

function App() {
  return (
    <div>
      <BrowserRouter>
        <HeaderComponent />
        <div className='container'>
          <Routes>
            <Route exact path='/' element={<ListClientComponent />}></Route>
            <Route path='/clients' element={<ListClientComponent />}></Route>
            <Route path='/add-client' element={<ClientFormComponent />}></Route>
            <Route path="/edit-client/:clientId" element={<ClientFormComponent />} />
          </Routes>
        </div>
        <FooterComponent />
      </BrowserRouter>
    </div>
  );
}

export default App;
