
import * as React from 'react';
import TextField from '@mui/material/TextField';
import { Button, Container, makeStyles, Paper } from '@material-ui/core';
import apiUrl from '../services/apiUrl';
import TitlebarBelowImageList from './ListImage';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *':{
      margin: theme.spacing(1),
      
    },
  },
}));
  

export default function BasicTextFields() {
  const [carros, setCarros] = React.useState([]);
  React.useEffect(() => {
    apiUrl.get("carro").then(({data}) => {
      setCarros(data.carros);
    });
      console.log(carros);
    
      // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const paperStyle ={padding:'50px 50px', width:1000,margin:"20px auto"}
  const [id,setId] = React.useState('')
  const [tipo,setTipo] = React.useState('')
  const [modelo,setModelo] =React.useState('')
  const [marca,setMarca] =React.useState('')
  const [ano,setAno] =React.useState('')
  const [price,setPrice] =React.useState('')
  const classes = useStyles();

  const [ListCarro, setListCarro] = React.useState([])
  const handleClick =(e)=>{
    e.preventDefault()
    const carroSave={id,tipo,modelo,marca,ano,price}
    console.log(carroSave)
    fetch("http://localhost:8080/carro/save",{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(carroSave)
    }).then(() =>{
      console.log("New Carro added")
    }) }

    const updateClick =(e)=>{
      e.preventDefault()
      const carroUpdate={id,tipo,modelo,marca,ano,price}
      console.log(carroUpdate)
      fetch("http://localhost:8080/carro",{
        method:"PUT",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(carroUpdate)
      }).then(() =>{
        console.log("Carro Updated")
      }) }
  
      React.useEffect(() =>{
        fetch("http://localhost:8080/carro")
        .then(res=>res.json())
        .then((result)=>{
          setListCarro(result);
        }
        )
      },[])
     

  return (

    <Container maxWidth="sm">
      <Paper>
      <TitlebarBelowImageList/>
      </Paper>
      <Paper elevation={3} style={paperStyle}>
       
      <h1 style={{color: "blue"}}>Editar Carro</h1>

        <form className={classes.root} noValidate autoComplete="off">

      
        
      <TextField id="filled-basic" label="Id" variant="filled" fullWidth
      value={id}
      onChange={(e)=> setId(e.target.value)}
       />
      <TextField id="filled-basic" label="Type" variant="filled" fullWidth
      value={tipo}
      onChange={(e)=> setTipo(e.target.value)}
       />
      <TextField id="filled-basic" label="Model" variant="filled" fullWidth
      value={modelo}
      onChange={(e)=> setModelo(e.target.value)}
       />
      <TextField id="filled-basic" label="Brand" variant="filled" fullWidth
      value={marca}
      onChange={(e)=> setMarca(e.target.value)}
      />
      <TextField id="filled-basic" label="Year" variant="filled" fullWidth
      value={ano}
      onChange={(e)=> setAno(e.target.value)}
      />
      <TextField id="filled-basic" label="Price" variant="filled" fullWidth
      value={price}
      onChange={(e)=> setPrice(e.target.value)}
      />
      <Button variant="contained" color="secondary" onClick={handleClick}>
        Submit
      </Button>
      <Button variant="contained" color="secondary" onClick={updateClick}>
        Update
      </Button>
      </form>
      
      </Paper>
      <h1>Carros</h1>
      <Paper elevation={3} style={paperStyle}>
        {ListCarro.map(list=>(
          <Paper elevation={6} style={{margin:"10px" ,padding:"15px", textAlign:"left"}} key={list.id}>
            Id:{list.id}<br/>
            Type:{list.tipo}<br/>
            Model:{list.modelo}<br/>
            Brand:{list.marca}<br/>
            Year:{list.ano}<br/>
            Price:{list.price}<br/>
            </Paper>
        ))}

      </Paper>
    </Container>
  );
}
