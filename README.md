# Desafio 1 - Framework Java: Spring Framework

## Controle de Transação e  Resiliência

Construir uma aplicação java8 para ler e armazenar os dados dos 4 arquivos de dados: data_1.json, data_2.json, data_3.json, data_4.json.
Espera-se o processamento mais rápido possível, considere usar paralelismo e pense bem como irá armazenar estes dados. Isso é muito importante pra gente =)
Caso a aplicação seja encerrada inesperadamente (via task manager ou caso dê um erro, etc), ela deverá ser inteligente e, ao subir novamente, ela não deverá processar os dados que já foram armazenados anteriormente. Deverá validar registros duplicados por arquivo.


### Arquitetura utilizada

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring AMQP](https://spring.io/projects/spring-amqp)
* [Spring Data](https://spring.io/projects/spring-data)

* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds



