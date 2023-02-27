# Varejonline - Projeto #

Projeto avaliativo para novos colaboradores.

## Para o funcionamento do projeto ##

* Foi utilizado a versão 16.15.0 do node
* Foi utilizado a versão 17 do Java

* Existe dois usuários pré-cadastrados no banco
    * username: gerente
    * senha: 123
    * username: operador
    * senha: 123

## Estrutura do Projeto ##

Dentro desse repositório pode-se encontrar 2 projetos, o ```frontend``` e o ```server```, sendo eles:

* ```frontend```: Projeto em **Angular** que deve conter todas a camada de apresentação do projeto (telas,
  formatações...).
    * A inicialização do projeto é feito pelo comando ```npm install``` onde será feito o download das dependências. (*
      *Obs.:** Necessário ter o _Angular CLI - 12.2.17^_).
    * A execução do projeto é feita pelo comando ```npm start``` que rodará o projeto na porta ```4200```.
    * Todas as chamadas serão feitas para o server através da porta ```8081``` já configurada
      no ```proxy-config.json```. Essa configuração faz com que toda a requisição para ```/server/...``` seja
      redirecionado para o server.
* ```server```: Projeto em **Java - Spring Boot** que deve conter todas as regras de negócio da aplicação, incluindo as
  regras de segurança de acesso.

