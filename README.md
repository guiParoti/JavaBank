🏦 Sistema Bancário em Java

Aplicação de console desenvolvida em Java que simula um sistema bancário com persistência de dados utilizando SQLite.
O projeto foi criado com foco em consolidar conceitos de Programação Orientada a Objetos, organização em camadas e manipulação de banco de dados com JDBC.

🚀 Funcionalidades

👤 Cadastro de cliente

🏦 Abertura de conta bancária

💰 Consulta de saldo

➕ Depósito com validação de valor

➖ Saque com verificação de saldo insuficiente

📄 Registro automático de transações

📊 Emissão de extrato completo

💾 Persistência de dados com SQLite

🛠 Tecnologias Utilizadas

Java

JDBC

SQLite

Programação Orientada a Objetos (POO)

Estrutura em camadas (Model / DAO / Interface)

🧠 Conceitos Aplicados

Encapsulamento

Separação de responsabilidades

DAO Pattern

Relacionamento 1:N (Conta → Transações)

Manipulação de List

Expressões Lambda

Tratamento de exceções

Validação de entrada de dados

Uso de toString() para exibição organizada

🗂 Estrutura do Projeto
📦 src
 ┣ 📂 aplicacao
 ┃ ┗ App.java
 ┣ 📂 bancoDados
 ┃ ┗ ConexaoSqlite.java
 ┣ 📂 dao
 ┃ ┣ ClienteDAO.java
 ┃ ┣ ContaDAO.java
 ┃ ┗ TransacaoDAO.java
 ┣ 📂 interfaces
 ┃ ┣ MenuInicial.java
 ┃ ┣ MenuLogin.java
 ┃ ┣ MenuCriarCadastro.java
 ┃ ┣ MenuAbrirConta.java
 ┃ ┗ MenuPrincipal.java
 ┗ 📂 modelo
   ┣ Cliente.java
   ┣ Conta.java
   ┗ Transacao.java

▶️ Como Executar

Clone o repositório:

git clone : https://github.com/guiParoti/JavaBank.git

Abra o projeto em sua IDE (Eclipse, IntelliJ, VS Code)

Execute a classe principal do sistema

Utilize o menu interativo no terminal

📈 Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de praticar:

Persistência de dados com SQLite

Estruturação de aplicações Java

Implementação de regras de negócio

Organização e refatoração de código

