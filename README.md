# Desafio 1

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

* <b> Spring-Producer:</b> este módulo visa executar os Beans na abertura da aplicação que realizam toda configuração do <b>Spring AMQP</b> não se prendento a nenhum Brocker específico de mensageria embora toda aplicação utilize RabbitMq. Está funcionando no início da aplicação por meio de uma API que realiza o envio da mensagem para o RabbitMq em Docker. É chamada na porta <b>8081</b> desde que a TCRProjetoUBS ou nenhuma outra aplicação esteja utilizando a mesma porta.

* <b> Spring-Consumer:</b> este módulo visa executar também os Beans na abertura da aplicação que realizam toda configuração do <b>Spring AMQP</b> não se prendento assim como a Producer a nenhum Brocker específico de mensageria embora toda aplicação utilize RabbitMq. 
Está funcionando no início da aplicação por meio de uma API que realiza o consumo da mensagem para o RabbitMq em Docker liberando a fila OU enviando para <i>DeadLetter</i> ou ainda para <i>Parking Lot</i>. É chamada na porta <b>8082</b> desde que a TCRProjetoUBS ou nenhuma outra aplicação esteja utilizando a mesma porta.

* Os micro-serviços estão acessando o RabbitMq pela porta <b>5672</b> confirme arquivo <b>YML</b> especificado nas aplicações respectivamente (application.yml).

* O RabbitMq por sua vez está rodando em Container Docker na portal <b>5672</b>.

### Como executar a aplicação
### Obs.: conforme o que foi desenvolvido até o momento.
* Para instalar o container Docker com RabbitMq:
$ docker run -d -p 15672:15672 -p 5672:5672 --name rabbitmq rabbitmq:3-management

* A aplicação (no estado atual de desenvolvimento) deverá ter o build realizado da Spring-Producer e/ou Spring-Consumer. O desenvolvimento preocupou-se com a ordem de de execução para evitar bug na execução. Para isso ambos os módulos foram desenvolvidos para serem independentes quanto a execução.

* Ao executar (build) das aplicações via Postman deverá ser executado da seguinte maneira:
    Na execução da Spring-Producer:
      - executar  localhost:8081/send
      no Body do Postman deve constar o Json (mensagem) a ser enviada ao <i>Routing Key</i>:
      {
        "text": "Este é um envio de mensagem de testes que será substituído pelo cadastro de usuários"
      }

* Para realizar o consumo da mensagem no RabbitMq de forma a baixar da <i>Routing Key</i> e enviá-la ao destino:
    Na execução da Spring-Consumer:
    - executar localhost:8082/repost
   A mensagem que estava na fila será printada no console como indicador de sucesso, ou seja, a mensagem foi baixada do <i>Routing Key</i> e no momento apenas apresentada na tela como simulação da Consumer.
   
 ### A aplicação está preparada para, de tempos em tempos, realizar nova aquisição de consumo da mensgem por um determinado volume de tentativas, após o volume programado de execuções e a mensagem não for consumida, será envida para <i>DeadLetter</i> para tratamento manual no Console do RabbitMq.
 ### Caso a fila seja excluída a aplicação está preparada para recriá-las.
 ### A aplicação ficou resiliente a quedas tanto na Producer quanto na Consumer além de tratar as filas que estão no RabbitMq.
 
 OBS: A aplicação foi desenvolvida utilizando o Spring AMQP procurando ao máximo torná-la independente do Brocker (RabbitMq, IbmMq, Kafka, etc.).

### Pendências para o Sucesso do Desafio 1:
*1 Criação de 4 containers para rodar de forma interdependente cada um dos serviços sendo:
    *1a) Container com a base de dados relacional que deverá receber os dados persistidos e controlados por meio de <i>Primary key</i>;
    *2a) Container com a aplicação que fará a Producer de envio para o Container do RabbitMq;
    *3a) Container que rodará a aplicação com a Consumer que fará a aquisição das informações para persistência no Container com Base de Dados relacional;
    *4a) Container com o RabbitMq;
* Consumo de multiplos arquivos por meio de Strem e Map (para utilização de paralelismo) constantes em uma pasta padrão; a aplicação está consumindo no momento o arquivo que for indicado;<p/>
* Tratativa para que a aplicação não repita os dados já persistidos;<p/>
* Conversão da aplicação para ao invés de chamar API seja na verdade invocado um micro-serviço de envio, consumo  das mensagens;<br />











