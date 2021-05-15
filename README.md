# Cadastro-de-Anuncios

>Status: Concluído

### Sistema de cadastro de anúncios desenvolvido por mim, aonde foi criado o CRUD de anúncios e relatórios.

## Funcionalidades
Através do sistema de cadastro de anúncios é possível cadastrar, consultar, alterar e excluir anúncios. Também é possível obter relatórios da quantidade de clientes cadastrados, quantidade de anúncios, valor total investido, quantidade de visualizações, quantidade de cliques e quantidade de compartilhamentos.
Para o CRUD foi implementado o recurso de busca ou consulta através do nome completo do anúncio ou parte deste.

## Tecnologias utilizadas:

+ MySQL 8.0;
+ Java

## Como compilar e executar o sistema:

1. Abra o projeto no software de desenvolvimento NetBeans IDE 8.2;
2. Crie um banco de dados chamado "db_anuncios" utilzando MySQL 8.0, use o servidor padrão "localhost", defina o usuário como "root" e defina a senha como "1234";
3. Crie dentro do banco de dados "db_anuncios" uma tabela chamada "ANUNCIOS" com a seguinte estrutura:
      
      create table ANUNCIOS (
      ID_ANUNCIO int auto_increment primary key,
      NOME_ANUNCIO varchar(50) not null,
      NOME_CLIENTE varchar(50) not null,
      DATA_INICIO varchar(10),
      DATA_FIM varchar(10),
      VLR_INVESTIDO_DIA float
      );
      
4. No NetBeans, execute o arquivo "TelaPrincipal.java" que está dentro do pacote "br.com.anuncios.telas".
Obs.: Caso seja necessário fazer alterações de servidor, usuário ou senha do banco de dados, esses ajustes poderão ser feitos na classe "Anuncios.java" que está dentro do pacote "br.com.anuncios.beans". Faça os ajustes dentro do método "getConexao".



## Instruções de utilização:

Com o sistema em execução...

1) Formulário de Cadastro - para abrir o Formulário de Cadastro, na barra de menu superior, clique no menu "Cadastro" e em seguida selecione a opção "Anúncios" ou use as teclas de atalho "Alt+A";

1.1. Cadastrar - caso não existam dados cadastrados, preencha os campos do formulário de cadastro e em seguida clique no botão "Adicionar" (botão com o sinal de mais);

1.2. Consultar - na tela de cadastro é possível consultar um anúncio já cadastrado apenas digitando parte do nome do anúncio no campo "Digite o nome de um anúncio para buscar" (primeiro campo do formulário), em seguida clique no botão com o ícone de uma lupa, logo ao lado do campo de busca.

1.3. Editar - após realizar uma consulta é possível editar os dados consultados apenas fazendo alterações nos dados diretamente nos campos e em seguida clicando no botão "Alterar" (botão com a figura de uma caneta). Obs.: caso não seja feito alterações em um cadastro, para reativar o botão "Adicionar" é preciso clicar no botão "Alterar" e finalizar a edição;

1.4. Excluir - para excluir um cadastro é necessário fazer a consulta e depois basta clicar no botão "Excluir".

2. Formulário de Relatório - para abrir o Formulário de Relatório, feche ou minimize o Formulário de Cadastro, em seguida, na barra de menu superior, clique no menu "Relatório" e em seguida selecione a opção "Anúncios" ou use as teclas de atalho "Alt+R";

2.1.  Filtros do Relatório de Anúncios - É possível filtrar o relatório utilizando o nome do cliente ou pela faixa de tempo. Para filtrar utilizando o nome do cliente, basta digitar o nome completo ou parte dele no campo "Nome do Cliente" e automaticamente o filtro será executado. Já para filtrar através da faixa de tempo, basta preencher os campos de "Data Início" e "Data Fim";

2.2. Para retornar a tela de cadastro é necessário fechar ou minimizar a tela de relatório e reabrir a tela de cadastro, conforme passos anteriores.

3. Ajuda - no menu "Ajuda" da barra de menu, na opção "Sobre" ou utilizando as teclas de atalho "Alt+F1", é possível obter informações do sistema.

4. Opções - no menu "Opções" da barra de menu, na opção "Sair" ou utilizando as teclas de atalho "Alt+F4", é possível sair do sistema.
