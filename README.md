# Playlist por temperatura API
API que disponibiliza uma playlist personalizada de	acordo com a temperatura da localidade do usuário.


### Detalhes da API Playlist por temperatura
A API REST Playlist por temperatura contém as seguintes características:  
* Projeto criado com Spring Boot e Java 11
* Banco de dados MySQL com JPA e Spring Data JPA
* Autenticação e autorização com Spring Security e tokens JWT (JSON Web Token)
* Migração de banco de dados com Flyway
* Testes unitários e de integração com JUnit
* Caching com EhCache


### Como executar a aplicação
A aplicação pode ser rodada através de um container no Docker. Os conteineres ficarão estruturados de forma semelhante à arquitetura abaixo:

![Arquitetura da aplicação em ambiente de desenvolvimento no DOCKER](https://www.javainuse.com/docker-7-14-min.JPG)

Primeiro baixe uma imagem do banco MySQL 8 e crie uma rede para comunicar com a aplicação.
```
sudo docker pull mysql:8
sudo docker network create playlist-por-temperatura--mysql
```

Depois crie o banco de dados da aplicação
```
sudo docker run --detach --name mysqldb --network playlist-por-temperatura--mysql --env MYSQL_ROOT_PASSWORD=root  --env MYSQL_DATABASE=playlist_por_temperatura --publish 3306:3306 mysql:8
```

Clone o projeto
```
git clone https://github.com/diegoqueres/playlist-por-temperatura.git
```

Construa a imagem do projeto
```
cd playlist-por-temperatura
sudo docker image build -t playlist-por-temperatura .
```

Subir e rodar um novo container a partir da imagem recém-criada
```
sudo docker container run --network playlist-por-temperatura--mysql --name playlist-por-temperatura-container -p 8080:8080 -d playlist-por-temperatura
```

Acesse os endpoints através da url http://localhost:8080.


### Documentação
* Acesse a documentação dos endpoints pela url http://localhost:8080/swagger-ui.html
* Acesse a documentação javadoc de classes pela pasta javadoc/ na raíz do projeto
