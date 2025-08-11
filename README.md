# PetI9

## 📌 Sobre o Projeto
O **PetI9** é uma aplicação backend desenvolvida para gerenciar informações de **Pets**, seus **Tutores** e **Vacinas**.  
O sistema implementa operações **CRUD** completas e conta com uma suíte de **testes unitários** com cobertura mínima de **75%**, validada via **SonarQube**.

O objetivo principal é fornecer uma base sólida para aplicações de gestão pet, com arquitetura organizada, código limpo e foco em boas práticas.

---

## 🚀 Tecnologias Utilizadas
- **Java** (versão recomendada: 17+)
- **Spring Boot** (REST API)
- **Maven** (build e gerenciamento de dependências)
- **JUnit** (testes unitários)
- **SonarQube** (análise de qualidade e cobertura)
- **H2 Database** (banco de dados em memória para testes)


---

## ⚙️ Funcionalidades
- **Pets**
  - Cadastro de novo pet
  - Atualização de dados
  - Listagem
  - Exclusão
- **Tutores**
  - CRUD completo
- **Vacinas**
  - Registro e vinculação ao pet
  - Atualização e exclusão
- **Testes**
  - Suíte completa de testes unitários
  - Cobertura mínima de 75% validada no SonarQube

---

## 📦 Como Executar o Projeto

### Pré-requisitos
- **Java 17** ou superior
- **Maven** instalado
- **SonarQube** (opcional, apenas para análise de qualidade)

### Passos
```bash
# Clonar o repositório
git clone https://github.com/GoularteLB/PetI9.git

# Entrar na pasta do projeto
cd PetI9/demo

# Executar a aplicação
mvn spring-boot:run

#A aplicação ficará disponível em:
http://localhost:8080

#Para análise de cobertura com o SonarQube:

mvn clean verify sonar:sonar


#Cobertura de Testes

Cobertura total acima de 75%


