<p align="center">
  <img src="https://seeklogo.com/images/U/UFPR-logo-F52F55C42F-seeklogo.com.png" />
</p>


# BANTADS

Projeto desenvolvido durante o curso de Análise e Desenvolvimento de Sistema, na Universidade Federal do Paraná. Consiste no desenvolvimento de um aplicativo bancário, com frotend em Angular, e backend em Java e NodeJS - microserviços.

## Estrutura

O projeto foi desenvolvido utilizando uma arquitetura de microserviços. Cada microserviço (Java) possuia seu próprio banco de dados, e respondia suas próprias chamadas, sejam elas por REST, ou por mensageria (RabbitMQ). Além disso, para as operações de cadastro e aprovação da conta bancária (que envolviam aterações em vários microserviços), foi utilizada uma SAGA orquestrada. Finalmente, o módulo de Conta, possuia um banco de dados em modelo CQRS, separando as operações de CUD e READ.

![image](https://user-images.githubusercontent.com/54849423/192165265-4b0589bb-66d4-477a-b9a4-62a83c87b152.png)


 
## Instalação
Precisa apenas do Docker instalado e dos arquivos do projeto

## Execução
Para rodar o projeto em sua máquina:
1. Rodar docker compose. Todos os serviços e banco de dados já estão configurados, e irão buildar e subir automaticamente.

```
docker compose up
```
