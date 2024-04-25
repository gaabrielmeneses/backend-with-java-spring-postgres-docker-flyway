# API para Gerenciamento de uma Granja de Patos

Este projeto consiste em uma API desenvolvida em Java com o framework Spring Boot para gerenciar uma granja de patos. A aplicação oferece endpoints para cadastro de patos, clientes, vendas, e também permite o download de relatórios de gerenciamento em formatos .xls e .pdf.

## Cronograma de Atividades

- Elaborar cronograma e estimativas - 30m
- Análise do problema e desenho das possíveis soluções - 1h
- MER e Modelagem do banco de dados - 2h
- Montagem Setup inicial e ambiente - 3h
- Implementação endpoint de cadastro dos patos - 30m
- Implementação endpoint de cadastro dos clientes - 30m
- Implementação endpoint de cadastro das vendas - 30m
- Implementação endpoint download relatório de gerenciamento de patos .xls - 2h
- Implementação endpoint download relatórios de gerenciamento de patos .pdf - 2h
- Teste unitário cadastro dos patos - 30m
- Teste unitário cadastro dos clientes - 30m
- Teste unitário de cadastro das vendas - 30m
- Teste unitário download relatório de gerenciamento de patos .xls - 30m
- Teste unitário download relatórios de gerenciamento de patos .pdf - 30m
- Implementação Flyway - 10m
- Dockerizando a aplicação - 20m
- Fazer descrição no README de inicialização do projeto e das tecnologias utilizadas - 1h

## MER

![image](https://github.com/gaabrielmeneses/backend-with-java-spring-postgres-docker-flyway/assets/47134065/15edee5b-6d63-400b-b3d2-77189fb86e16)


## Tecnologias Utilizadas

- Java (Amazon Corretto 19.0.2)
- Spring Boot (3.2.5)
- Pgadmin 4.0.0
- PostgresSQL
- Flyway
- Docker
- JUnit5
- Mockito
- Jeasy (5.0.0)
- ItextPdf (7.2.0)
- Apache POI (5.2.4)
- Supabase

## Padrões Utilizados

- Arquitetura hexagonal (Arquitetura limpa)
- Princípios SOLID
- Builder
- Paradigmas: Funcional e OOP
- Testes unitários da camada de domínio (domain)

## Execução do Projeto

### Download do Projeto

- Clone o projeto em sua máquina utilizando o comando:
  ```
  git clone https://github.com/gaabrielmeneses/backend-with-java-spring-postgres-docker-flyway.git
  ```

### Rodando a Aplicação

#### Utilizando Docker:

- Crie um arquivo `docker-compose.yml` na pasta do projeto com o seguinte conteúdo:
  ```yaml
  version: '3.9'
  services:
    backend-with-java-spring-postgres-docker-flyway:
      image: gaabrielmeneses/backend-with-java-spring-postgres-docker-flyway
      restart: always
      build: ./backend-with-java-spring-postgres-docker-flyway
      working_dir: /backend-with-java-spring-postgres-docker-flyway
      environment:
        TZ: America/Sao_Paulo
      ports:
        - 8080:8080
      command: mvn spring-boot:run
      networks:
        - menesic-network
  networks:
    menesic-network:
      driver: bridge
  ```
- Execute o comando no terminal para subir a aplicação:
  ```
  docker compose up -d --build
  ```
- Importe a collection `src\main\resources\collection\GranjaPatoApp.postman_collection.json` para o Postman.
- Crie uma variável de ambiente chamada `endpoint-gateway` com o valor `http://localhost:8080` no Postman.
- Teste os endpoints da API.

#### Rodando a aplicação localmente:

- Abra o projeto no IntelliJ ou na IDE de sua preferência.
- Configure a aplicação para build e run com o Java Corretto 19.
- Execute a aplicação.
- A aplicação estará pronta para uso e pode ser testada seguindo os mesmos passos da seção utilizando Docker.
## Observação Importante

- O retorno do endpoint de download dos relatórios devem ser salvos com a extensão de arquivo correspondente para serem abertos corretamente, ou seja, utilize ".xls" para o relatório em formato Excel e ".pdf" para o relatório em formato PDF.

Qualquer dúvida pode me mandar mensagem no linkedin -> https://www.linkedin.com/in/gabriel-meneses-da-silva-nascimento-49a18118a/
