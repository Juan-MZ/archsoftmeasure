package co.unicauca.archsoftmeasure.util.response.handler;

import co.unicauca.archsoftmeasure.util.response.Response;
import lombok.Getter;

@Getter
public class ResponseHandler<T> {
    private final Response<T> response;

    public ResponseHandler(int status, String userMessage, String infoRuta, T data) {
        response = new Response<>();

        response.setStatus(status);
        response.setUserMessage(userMessage);
        response.setRouteInfo(infoRuta);
        response.setData(data);
    }
}