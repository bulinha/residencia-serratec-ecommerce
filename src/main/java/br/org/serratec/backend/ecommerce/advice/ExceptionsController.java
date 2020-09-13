package br.org.serratec.backend.ecommerce.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.org.serratec.backend.ecommerce.exception.DataNotFoundException;
import br.org.serratec.backend.ecommerce.exception.NotAllowedUpdateException;

@RestControllerAdvice
public class ExceptionsController {

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(DataNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}
	
	@ExceptionHandler(NotAllowedUpdateException.class)
	public ResponseEntity<Void> trataParametroObrigatorio(NotAllowedUpdateException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.status(HttpStatus.FORBIDDEN).header("x-erro-msg", mensagem).build();

	}
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> trataValidacoes(MethodArgumentNotValidException exception) {
		Map<String, String> errosMap = new HashMap<String, String>();
		List<ObjectError> errosEncontrados = exception.getBindingResult().getAllErrors();
		for (ObjectError erro : errosEncontrados) {
			FieldError fieldError = (FieldError) erro;
			String atributo = fieldError.getField();
			String mensagem = fieldError.getDefaultMessage();
			errosMap.put(atributo, mensagem);
		}
		return ResponseEntity.badRequest().body(errosMap);
	}
}
