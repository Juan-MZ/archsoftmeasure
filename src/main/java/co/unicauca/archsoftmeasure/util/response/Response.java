package co.unicauca.archsoftmeasure.util.response;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO genérico para las respuestas de las peticiones a los servicios Rest.
 * Este encapsula el resultado del consumo de las Apis y le adiciona atributos de control.
 */
@Getter
@Setter
public class Response<T> {

	/** Indica el estado de la transacción */
	private int status;

	/** Mensaje informativo para el usuario */
	private String userMessage;

	/** Ruta de acceso */
	private String routeInfo;

	/** Objeto con la respuesta de la transacción */
	private T data;

	@Override
	public String toString() {
		return "Response [state=" + status + ", userMessage=" + userMessage + ", routeInfo=" + routeInfo + ", data="
				+ data + "]";
	}
}
