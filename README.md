**Teste Mercado Livre - Simian**

_Felipe Sousa_

Código fonte se encontra no github como privado, e foi dado
acesso ao usuário *ITMLB* como colaborador.<br>
Link: https://github.com/fsousaprog/testeML.git

O app se encontra disponível em uma instância do Google App Engine.<br> 
URL: https://testemlsimian-lhyrswhb5q-rj.a.run.app <br>
Endpoint: /simian

* Para realizar a verificação de um simio, basta enviar um arquivo
JSON via **POST** para o endpoint **/simian** contendo a 
tag *dna* com a sequencia de dna como valor.
* JSON de exemplo: 
  * { "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"] }
  
Caso o DNA seja identificado como um símio, será retornado um HTTP 200-OK,
e caso contrário um HTTP 403-FORBIDDEN