package common.service;


import common.model.Response;
import common.model.exceptions.NoSuchUserException;

public interface IService {


    /*when client connects to client he send as parameter his name
    * method returns string access token
    * client will use that token to communiate with client another time
    * such a way client will know with which of all users it works exactly now*/
    String registerToGame(String clientName);


    /*after user set his 6 numbers client app sends it`s token with that numbers
    * client build due to the accesskey of user response
    * so for each user is generated it`s own 6 numbers on client
    * this numbers will store in some map (key, value) => (accessKey, numbersGeneratedOnServer)
    * and when user send its token and numbers it just compare with numbers on client
    * and build response*/
    Response isIWinner(String token, Integer[] numbers) throws NoSuchUserException;


}
