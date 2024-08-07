

### Pacote e Importações

```java
package me.estudos.api_rest_nuvem.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.estudos.api_rest_nuvem.domain.model.User;
import me.estudos.api_rest_nuvem.service.UserService;
```

- `package me.estudos.api_rest_nuvem.controller;`: Define o pacote onde essa classe está localizada.
- As importações trazem várias classes necessárias para o funcionamento do controlador. Elas incluem classes para mapeamento de solicitações HTTP (`GetMapping`, `PostMapping`, etc.), manipulação de respostas HTTP (`ResponseEntity`), construção de URIs (`ServletUriComponentsBuilder`), e as classes de domínio e serviço (`User`, `UserService`).

### Anotações e Classe

```java
@RestController
@RequestMapping("/users")
public class UserController {
```

- `@RestController`: Indica que esta classe é um controlador Spring REST, responsável por lidar com requisições HTTP e devolver respostas apropriadas em formato JSON ou XML.
- `@RequestMapping("/users")`: Mapeia as requisições HTTP que começam com `/users` para os métodos dentro desta classe.

### Injeção de Dependência e Construtor

```java
private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}
```

- `private final UserService userService;`: Declara uma dependência para o `UserService`.
- O construtor `UserController(UserService userService)` é usado para injeção de dependência. Isso significa que o Spring irá injetar uma instância de `UserService` quando criar uma instância de `UserController`.

### Método para Buscar Usuário por ID

```java
@GetMapping("/{id}")
public ResponseEntity<User> findById(@PathVariable Long id) {
    var user = userService.findById(id);
    return ResponseEntity.ok(user);
}
```

- `@GetMapping("/{id}")`: Mapeia requisições HTTP GET para `/users/{id}`, onde `{id}` é um parâmetro de caminho.
- `@PathVariable Long id`: Extrai o valor `{id}` da URL e o passa como argumento para o método.
- `userService.findById(id)`: Chama o método `findById` do `UserService` para buscar o usuário pelo ID.
- `ResponseEntity.ok(user)`: Retorna uma resposta HTTP 200 (OK) contendo o usuário encontrado.

### Método para Criar um Novo Usuário

```java
@PostMapping
public ResponseEntity<User> create(@RequestBody User userToCreate) {
    var userCreated = userService.create(userToCreate);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
         .path("/{id}")
         .buildAndExpand(userCreated.getId())
         .toUri();
    return ResponseEntity.created(location).body(userCreated);
}
```

- `@PostMapping`: Mapeia requisições HTTP POST para `/users`.
- `@RequestBody User userToCreate`: Extrai o corpo da requisição HTTP e o desserializa para um objeto `User`.
- `userService.create(userToCreate)`: Chama o método `create` do `UserService` para criar um novo usuário.
- `ServletUriComponentsBuilder.fromCurrentRequest()`: Constrói a URI do recurso recém-criado.
  - `path("/{id}")`: Adiciona o ID do novo usuário à URI.
  - `buildAndExpand(userCreated.getId())`: Substitui `{id}` pelo ID do usuário criado.
  - `toUri()`: Converte a URI construída para um objeto `URI`.
- `ResponseEntity.created(location).body(userCreated)`: Retorna uma resposta HTTP 201 (Created) com a URI do novo recurso no cabeçalho `Location` e o corpo contendo o usuário criado.

### Resumo

O `UserController` é um controlador REST que expõe dois endpoints:

1. `GET /users/{id}`: Busca um usuário pelo ID e retorna os detalhes do usuário.
2. `POST /users`: Cria um novo usuário com os dados fornecidos no corpo da requisição e retorna o usuário criado junto com a URI do novo recurso no cabeçalho `Location`.

Essa abordagem segue as melhores práticas do RESTful, incluindo o uso de códigos de status HTTP apropriados e a construção de URIs para recursos recém-criados.