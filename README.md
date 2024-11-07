# RH Company - Sistema de Controle de Funcionários e Remuneração #
Este repositório contém a aplicação RH Company, um sistema desenvolvido para gerenciar o cadastro de funcionários e controlar suas remunerações. Utilizando tecnologias modernas como Angular para a interface do usuário e Spring Boot para a API back-end, a aplicação foi projetada seguindo uma arquitetura em camadas para garantir a modularidade e a escalabilidade.

Funcionalidades Principais
Cadastro de Funcionários: Criação e manutenção dos registros de funcionários, incluindo informações de setor, cargo, e status.
Gestão de Remunerações: Registro e consulta das remunerações associadas a cada funcionário.
Administração de Setores: Cadastro e organização dos setores da empresa.
Autenticação e Autorização: Controle de acesso através de perfis de usuários.
Tecnologias Utilizadas
Front-end: Angular
Back-end: Spring Boot
Banco de Dados: PostgreSQL
ORM: Hibernate/JPA
Outras: Lombok, JUnit para testes, Docker para containerização.
Estrutura do Projeto
O projeto segue uma arquitetura em camadas, dividida em:

Domain: Modelos de entidades mapeadas para a persistência.
Repository: Interfaces de repositórios para acesso ao banco de dados.
Service: Implementação da lógica de negócios.
Controller: Endpoints para a interação com o cliente.
Como Executar
Clone o repositório: git clone https://github.com/seu-usuario/rh-company.git
Navegue para a pasta do projeto e execute ./mvnw spring-boot:run para iniciar a API.
Abra a pasta client e use ng serve para iniciar o front-end Angular.
Acesse http://localhost:4200 para usar a aplicação.
