# Desafio 1 - Framework Java: Spring Framework

## Controle de Transação e  Resiliência

Construir uma aplicação java8 para ler e armazenar os dados dos 4 arquivos de dados: data_1.json, data_2.json, data_3.json, data_4.json.
Espera-se o processamento mais rápido possível, considere usar paralelismo e pense bem como irá armazenar estes dados. Isso é muito importante pra gente =)
Caso a aplicação seja encerrada inesperadamente (via task manager ou caso dê um erro, etc), ela deverá ser inteligente e, ao subir novamente, ela não deverá processar os dados que já foram armazenados anteriormente. Deverá validar registros duplicados por arquivo.


### Arquitetura utilizada

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring AMQP](https://spring.io/projects/spring-amqp)
* [Spring Data](https://spring.io/projects/spring-data)
* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com)
* [Rabbitmq](rabbitmq.com)

### Relatório Geral do Desenvolvimento
O sistema foi desenvolvido com os objetivos de consumir 4 arquivos no formato .Json tendo sido utilizado os padrões arquiteturais de código Java e as anotações características do Spring Framework conforme citado acima.

Ele foi desenvolvido em 3 módulos mais a instalação até o momento de um Container Docker que está rodando RabbitMq e o respectivo gerenciador de mensageria.

A concepção e a definição de requisitos previu que cada módulo deve ocupar um micro-serviço em container com objetivos específicos que visem utilizar a melhor tecnologia no quesito de controle transacional e de resiliência na aquisição, armazenamento e disponbilidade dos dados.

Não foi finalizado o projeto face o tempo demandado para entrega; entretanto, tem-se pelo código exposto neste repositório todo padrão arquitetural necessário ao atendimento do desafio proposto.

### Especificação de Cada Módulo

* <b> TCRProjetoUBS:</b> este módulo foi desenvolvido  para realizar o acesso e a leitura dos arquivos .Json e persistílos no serviço de mensasgeria. Até o momento o mesmo está persistindo os dados em uma base de dados H2 (banco de dados em memória). Obedecendo as regras de persistências características do Spring Data.

* <b> Spring-Consumer-Producer:</b> este módulo visa executar os Beans na abertura da aplicação que realizam toda configuração do <b>Spring AMQP</b> não se prendento a nenhum Brocker específico de mensageria embora toda aplicação utilize RabbitMq. Está funcionando no início da aplicação por meio de uma API que realiza o envio da mensagem para o RabbitMq em Docker.

* <b> Spring-Consumer-Producer:</b> este módulo visa executar os Beans na abertura da aplicação que realizam toda configuração do <b>Spring AMQP</b> não se prendento a nenhum Brocker específico de mensageria embora toda aplicação utilize RabbitMq. Está funcionando no início da aplicação por meio de uma API que realiza o envio da mensagem para o RabbitMq em Docker.






