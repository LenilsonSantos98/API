Vamos detalhar a camada de negócio  que consiste na interface `UserService` e na implementação `UserServiceImpl`. Essa camada é responsável pela lógica de negócio associada ao gerenciamento de usuários na sua aplicação.

### Interface `UserService`

A interface `UserService` define o contrato que a implementação deve seguir. Ela contém dois métodos principais:

```java
package me.estudos.api_rest_nuvem.service;

import me.estudos.api_rest_nuvem.domain.model.User;

public interface UserService {
    User findById(Long id);

    User create(User userToCreate);
}
```

1. **`User findById(Long id);`**:
   - Esse método é responsável por encontrar um usuário pelo seu ID. Ele retorna um objeto `User` correspondente ao ID fornecido.
   
2. **`User create(User userToCreate);`**:
   - Esse método é responsável por criar um novo usuário. Ele recebe um objeto `User` que representa o usuário a ser criado e retorna o objeto `User` criado.

### Implementação `UserServiceImpl`

A classe `UserServiceImpl` é uma implementação da interface `UserService`. Ela contém a lógica real para as operações definidas na interface. Vamos detalhar o código:

```java
package me.estudos.api_rest_nuvem.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.estudos.api_rest_nuvem.domain.model.User;
import me.estudos.api_rest_nuvem.domain.repository.UserRepository;
import me.estudos.api_rest_nuvem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User userToCreate) {
        if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("this Account number already exists");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new );
    }
}
```

#### Explicação Detalhada:

1. **Anotação `@Service`**:
   - A anotação `@Service` indica que esta classe é um componente de serviço do Spring. Isso a torna um candidato para detecção automática de componentes quando a aplicação é configurada com varredura de componentes (`component scanning`).

2. **Injeção de Dependência**:
   - A implementação recebe um `UserRepository` no seu construtor, que é armazenado em um campo final. O Spring gerencia a injeção de dependência automaticamente, fornecendo uma instância de `UserRepository` quando `UserServiceImpl` é instanciado.

3. **Método `create`**:
   ```java
   @Override
   public User create(User userToCreate) {
       if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
           throw new IllegalArgumentException("this Account number already exists");
       }
       return userRepository.save(userToCreate);
   }
   ```
   - **Validação**: Antes de criar um novo usuário, o método verifica se já existe uma conta com o mesmo número (`accountNumber`). Se existir, lança uma exceção `IllegalArgumentException`.
   - **Criação do Usuário**: Se a conta não existir, o método salva o novo usuário no repositório (`userRepository.save(userToCreate)`) e retorna o usuário criado.

4. **Método `findById`**:
   ```java
   @Override
   public User findById(Long id) {
       return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
   }
   ```
   - **Busca por ID**: O método tenta encontrar um usuário pelo seu ID usando o repositório. 
   - **Tratamento de Exceção**: Se o usuário não for encontrado, lança uma exceção `NoSuchElementException`.

### Resumo

- **Interface `UserService`**: Define os métodos para operações de usuário.
- **Classe `UserServiceImpl`**: Implementa a lógica de negócio dos métodos definidos na interface.
  - **`create`**: Verifica se a conta do usuário já existe e, se não, salva o novo usuário.
  - **`findById`**: Procura um usuário pelo ID e lança uma exceção se não for encontrado.

Esta camada de negócio permite encapsular a lógica de negócios associada ao gerenciamento de usuários, garantindo que todas as regras e verificações necessárias sejam aplicadas antes de qualquer operação no banco de dados.