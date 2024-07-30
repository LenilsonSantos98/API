# java RESTful API 
## Json
![alt text](image.png)
Criada a entidade usuário, a Lista de Features, a entidade cartão e a lista de novidades.

em seguida joguei o json no chatGPT e pedi para  gerar a diagramação de classes na sintaxe MERMAID.

## Diagrama de classes

```mermaid
classDiagram
  class User {
    -String name
    -Account account
    -Feature[] features
    -Card card
    -News[] news
  }

  class Account {
    -String number
    -String agency
    -Number balance
    -Number limit
  }

  class Feature {
    -String icon
    -String description
  }

  class Card {
    -String number
    -Number limit
  }

  class News {
    -String icon
    -String description
  }

  User "1" *-- "1" Account
  User "1" *-- "N" Feature
  User "1" *-- "1" Card
  User "1" *-- "N" News
```