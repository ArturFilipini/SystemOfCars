import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { TextField } from '@material-ui/core';

export default function ButtonAppBar() {
  const [username, setUsername] = React.useState('')
  const [password, setPassword] = React.useState('')

  const DoLogin =(e)=>{
    e.preventDefault()
    const carroLogin={username, password}
    console.log(username, password)
    fetch("http://localhost:8080/perfom_login",{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(carroLogin)
    }).then(() =>{
      console.log("Login sucessful")
    }) }

 
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <MenuIcon/>
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            List Of Cars
          </Typography>
          <TextField id="filled-basic" label="Username" variant="outlined"   
           value={username}
            onChange={(e)=> setUsername(e.target.value)}     />
          <TextField id="filled-basic" label="Password" variant="outlined"
           value={password}
            onChange={(e)=> setPassword(e.target.value)}        />
          <Button color="inherit" onClick={DoLogin}>Login</Button>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
