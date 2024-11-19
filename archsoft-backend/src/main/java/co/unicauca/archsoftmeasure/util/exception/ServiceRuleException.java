package co.unicauca.archsoftmeasure.util.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ResourceBundle;

public class ServiceRuleException extends RuntimeException {
    public ServiceRuleException(String message) {
        super(getMessage(message));
    }

    /**
     * Referencia a excepciones en resources
     */
    private static final ResourceBundle messages =
            ResourceBundle.getBundle("exceptions", LocaleContextHolder.getLocale());

    /**
     * Obtiene un mensaje del archivo de excepciones a través de una llave.
     *
     * @param key la llave para obtener el mensaje.
     * @return retorna el mensaje obtenido.
     */
    private static String getMessage(String key) {
        return messages.getString(key);
    }
}
