package my.control;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс-помощник для работы с контроллером
 * @author gusev.aleksandr
 * @since 24.09.2017
 */
@ControllerAdvice
public class MainContollerAdvice {

	/**
	 * В случае возникновения исключения {@link IllegalArgumentException}
	 * отдаем клиенту соответствующую ошибку
	 * @param response формируемый ответ
	 * @throws IOException
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	void handleBadRequests(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.FORBIDDEN.value(), "Не найден введенный город");
	}
}
