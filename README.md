# drone-feeder
Projeto final da aceleração java.
- `API se baseia no monitoramento com drones, a partir da localização enviando videos como entregas.`
- `API precisa que o usuário esteja logado e devidamente autenticado.`

## Exemplo do token na requisição:
`Bearer <token_aqui>`

## Clonar a aplicação:
`git clone git@github.com:reinaldoper/drone-feeder.git`
## Instalar as dependências:
`mvn install`
## Rodar o docker compose:
`docker-compose up -d`

## A Aplicação estara rodando:
`http://localhost:8080/`

## Rotas:
<details>
  <summary><strong>Rotas de entregas</strong></summary>

### - Endpoint para listar entregas:
  
- O endpoint deve ser acessível através do caminho (`/entregas`) e (`/entregas/id`);
  
### - Endpoint para criar entregas (exemplo):
  
`{
  "status": "pendente",
  "droneId": 1,
  "videoId": 1
}`
  
### - Endpoint para mudar o status:
  
- O Endpoint de ser acessível através do caminho (`/entregas/{id}/{status}`);
  
### - Endpoint para deletar entrega:
  
- O endpoint deve ser acessível através do caminho (`/entregas/id`);

### - Endpoint para alterar uma entrega:
  
- O endpoint deve ser acessível através do caminho (`/entregas/id`);
  
`{
  "videoId": 1
}`
  
</details>

<details>
  <summary><strong>Rotas de drones</strong></summary>

### - Endpoint para listar drones:
  
- O endpoint deve ser acessível através do caminho (`/drones`) e (`/drones/id`);
  
### - Endpoint para criar drones (exemplo):
  
`{
  "latitude": 37.7749,
  "longitude": -122.4194
}`
  
### - Endpoint para deletar drones:
  
- O endpoint deve ser acessível através do caminho (`/drones/id`);

### - Endpoint para alterar drones:
  
- O endpoint deve ser acessível através do caminho (`/drones/id`);
  
`{
  "latitude": 37.7749,
  "longitude": -122.4194
}`
  
</details>

<details>
  <summary><strong>Rotas de videos</strong></summary>

### - Endpoint para listar videos:
  
- O endpoint deve ser acessível através do caminho (`/videos`) e (`/videos/id`);
  
### - Endpoint para criar videos (exemplo):
  
`{"nomeArquivo": "video1.mp4"}`
  
### - Endpoint para deletar videos:
  
- O endpoint deve ser acessível através do caminho (`/videos/id`);

### - Endpoint para alterar videos:
  
- O endpoint deve ser acessível através do caminho (`/videos/id`);
  
`{"nomeArquivo": "video1.mp4"}`
  
</details>

<details>
  <summary><strong>Rotas de usuário:</strong></summary>

### 🔐 Autenticação e gerenciamento de usuários

- **POST `/auth`**  
  Cria um novo usuário com os dados fornecidos no corpo da requisição (`SaveRequest`).

- **POST `/auth/login`**  
  Realiza login com e-mail e senha, retornando um token JWT.

- **GET `/auth/{id}`**  
  Retorna os dados de um usuário com base no ID informado.

- **PUT `/auth/{id}`**  
  Atualiza os dados de um usuário com base no ID e nos dados enviados (`UpdateRequest`).

- **DELETE `/auth/{id}`**  
  Remove um usuário do sistema com base no ID informado.

</details>




