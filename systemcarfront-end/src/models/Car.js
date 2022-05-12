
import * as React from 'react';
import TextField from '@mui/material/TextField';
import { Button, Container, makeStyles, Paper } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *':{
      margin: theme.spacing(1),
    },
  },
}));

export default function BasicTextFields() {
  const paperStyle ={padding:'50px 50px', width:600,margin:"20px auto"}
  const [id,setId] = React.useState('')
  const [tipo,setTipo] = React.useState('')
  const [modelo,setModelo] =React.useState('')
  const [marca,setMarca] =React.useState('')
  const [ano,setAno] =React.useState('')
  const [price,setPrice] =React.useState('')
  const classes = useStyles();

  const handleClick =(e)=>{
    e.preventDefault()
    const carro={id,tipo,modelo,marca,ano,price}
    console.log(carro)
   
}
  return (

    <Container>
      <Paper elevation={3} style={paperStyle}>
      <h1 style={{color: "blue"}}>Editar Carro</h1>

        <form className={classes.root} noValidate autoComplete="off">

      <TextField id="outlined-basic" label="Id" variant="outlined" fullWidth
      value={id}
      onChange={(e)=> setId(e.target.value)}
       />
      <TextField id="outlined-basic" label="Type" variant="outlined" fullWidth
      value={tipo}
      onChange={(e)=> setTipo(e.target.value)}
       />
      <TextField id="outlined-basic" label="Model" variant="outlined" fullWidth
      value={modelo}
      onChange={(e)=> setModelo(e.target.value)}
       />
      <TextField id="outlined-basic" label="Brand" variant="outlined" fullWidth
      value={marca}
      onChange={(e)=> setMarca(e.target.value)}
      />
      <TextField id="outlined-basic" label="Year" variant="outlined" fullWidth
      value={ano}
      onChange={(e)=> setAno(e.target.value)}
      />
      <TextField id="outlined-basic" label="Price" variant="outlined" fullWidth
      value={price}
      onChange={(e)=> setPrice(e.target.value)}
      />
      <Button variant="contained" color="secondary" onClick={handleClick}>
        Submit
      </Button>
      </form>
      </Paper>
    </Container>
  );
}
