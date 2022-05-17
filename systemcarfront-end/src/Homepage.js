import { Box, Button } from "@mui/material";
import ButtonAppBar from "./models/Barra";
import BasicTextFields from "./models/Car";

function Homepage(){
    return(
        <div className="Homepage">
        <Box > 
     <ButtonAppBar/>
     </Box> 
      
      <BasicTextFields/>
      <Button>
      </Button>
      </div>
    )
}
export default Homepage;