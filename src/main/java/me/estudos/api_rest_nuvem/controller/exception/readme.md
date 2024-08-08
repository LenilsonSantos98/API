`GlobalExceptionHandler`:

### Pacote e Importações

```java
package me.estudos.api_rest_nuvem.controller.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
```

- `package me.estudos.api_rest_nuvem.controller.exception;`: Define o pacote onde essa classe está localizada.
- As importações trazem várias classes necessárias para o funcionamento do manipulador de exceções, incluindo exceções (`NoSuchElementException`), ferramentas de logging (`Logger`, `LoggerFactory`), classes para manipulação de respostas HTTP (`ResponseEntity`, `HttpStatus`), e anotações do Spring para tratamento de exceções (`ExceptionHandler`, `RestControllerAdvice`).

### Anotações e Declaração da Classe

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
```

- `@RestControllerAdvice`: Indica que esta classe fornece conselhos globais para todos os controladores REST. É usada para capturar e tratar exceções lançadas em qualquer lugar da aplicação de forma centralizada.

### Logger

```java
private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
```

- Cria um logger para a classe `GlobalExceptionHandler` usando o `LoggerFactory` do SLF4J. O logger é usado para registrar mensagens de erro.

### Manipuladores de Exceções

#### IllegalArgumentException

```java
@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<String> handle(IllegalArgumentException businessException){
    return new ResponseEntity<>(businessException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
}
```

- `@ExceptionHandler(IllegalArgumentException.class)`: Este método será chamado quando uma `IllegalArgumentException` for lançada em qualquer lugar da aplicação.
- `businessException.getMessage()`: Retorna a mensagem da exceção.
- `HttpStatus.UNPROCESSABLE_ENTITY`: Retorna um código de status HTTP 422, indicando que a entidade não pode ser processada.

#### NoSuchElementException

```java
@ExceptionHandler(NoSuchElementException.class)
public ResponseEntity<String> handle(NoSuchElementException notFoundException){
    return new ResponseEntity<>("Resource ID not found.", HttpStatus.NOT_FOUND);
}
```

- `@ExceptionHandler(NoSuchElementException.class)`: Este método será chamado quando uma `NoSuchElementException` for lançada.
- `"Resource ID not found."`: Retorna uma mensagem de erro padrão indicando que o recurso não foi encontrado.
- `HttpStatus.NOT_FOUND`: Retorna um código de status HTTP 404, indicando que o recurso solicitado não foi encontrado.

#### Throwable (Exceções não Esperadas)

```java
@ExceptionHandler(Throwable.class)
public ResponseEntity<String> handleUnexpectedException(Throwable unexpectedException){
    var message = "Unexpected server error, see the logs.";
    logger.error("message", unexpectedException);
    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
}
```

- `@ExceptionHandler(Throwable.class)`: Este método será chamado para qualquer exceção não tratada especificamente pelos outros manipuladores.
- `"Unexpected server error, see the logs."`: Retorna uma mensagem de erro genérica indicando que ocorreu um erro inesperado no servidor.
- `logger.error("message", unexpectedException)`: Registra a exceção inesperada usando o logger.
- `HttpStatus.INTERNAL_SERVER_ERROR`: Retorna um código de status HTTP 500, indicando que ocorreu um erro interno no servidor.

### Resumo

O `GlobalExceptionHandler` é um controlador de exceções global para sua aplicação Spring Boot, que captura e trata exceções de forma centralizada. Ele define manipuladores para:

1. **`IllegalArgumentException`**: Retorna um HTTP 422 com a mensagem da exceção.
2. **`NoSuchElementException`**: Retorna um HTTP 404 com uma mensagem padrão "Resource ID not found."
3. **Qualquer outra exceção (`Throwable`)**: Retorna um HTTP 500 com uma mensagem genérica e registra a exceção nos logs.

Esta abordagem ajuda a manter o código de tratamento de exceções separado da lógica do controlador, melhorando a legibilidade e a manutenção do código.