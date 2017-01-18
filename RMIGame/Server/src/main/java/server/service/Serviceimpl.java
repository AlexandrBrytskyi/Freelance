package server.service;

import common.model.Response;
import common.model.UserData;
import common.model.exceptions.NoSuchUserException;
import common.service.IService;
import server.gui.ServerGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component(value = "my_rmi_service")
public class Serviceimpl implements IService {

    @Autowired
    private ServerGUI serverGUI;

    private Map<String, UserData> usersAndNumbers = new HashMap<>();

    @Override
    public String registerToGame(String clientName) {
        String token = TokenGenerator.getToken();
        UserData newUserData = new UserData(generateNumbers(), clientName);
        usersAndNumbers.put(token, newUserData);
        serverGUI.printMessage("Client " + clientName + " has connected to client \n" +
                "we generated for him numbers " + Arrays.toString(newUserData.getNumbersForUser()));
        return token;
    }

    private Integer[] generateNumbers() {
        Integer[] res = new Integer[6];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 50);
        }
        return res;
    }

    @Override
    public Response isIWinner(String token, Integer[] numbers) throws NoSuchUserException {
        if (!usersAndNumbers.containsKey(token)) throw new NoSuchUserException("No such user, register first");
        UserData data = usersAndNumbers.get(token);
        Response response = buildResponse(numbers, data);
        serverGUI.printMessage("Client " + data.getUserName() + " is " +
                (response.isWinner() ? "Winner " : "Loser") +
                "\n builded response " + response.toString());
        return response;
    }

    private Response buildResponse(Integer[] numbers, UserData data) {
        Integer[] numbersWhichAreSimilar = findSimilarNumbers(numbers, data.getNumbersForUser());
        boolean isWinner = numbersWhichAreSimilar.length > 0;
        Response response = new Response(isWinner, numbersWhichAreSimilar, data.getNumbersForUser(),
                isWinner ? "Congratulations, " + data.getUserName() + "!!!" : "Sorry, " + data.getUserName() + ", you lose :(");

        return response;
    }

    private Integer[] findSimilarNumbers(Integer[] numbers, Integer[] numbersOnServer) {
        List numList = Arrays.asList(numbersOnServer);
        List<Integer> resList = new ArrayList<>();
        for (int number : numbers) {
            if (numList.contains(number)) {
                resList.add(number);
            }
        }
        Integer[] res = new Integer[resList.size()];
        return resList.toArray(res);
    }
}
