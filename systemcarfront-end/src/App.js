import './App.css';
import api from './services/api';
import { useEffect, useState } from 'react';
import ButtonAppBar from './models/Barra';
import BasicTextFields from './models/Car';
import { Box } from '@material-ui/core';
function App() {
  const [carros, setCarros] = useState([]);
  useEffect(() => {
    api.get("Carro").then(({data}) => {
      setCarros(data.carros);
    });
      console.log(carros);
    
      // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  return (
    <div className="App">
      <Box> 
     <ButtonAppBar/>
     </Box>  
      <BasicTextFields/>
    </div>
  );
}

export default App;
