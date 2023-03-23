@Test
public void registerClientTest(){
    try{
        ClientDTO clientDTo = new ClientDTO("Pepito", "pepe@gmail.com", "asdsfgsfdg")
        clientService.createClient(clientDTO);

    }catch(Exception e){
        e.printStackTrace();
    }
}