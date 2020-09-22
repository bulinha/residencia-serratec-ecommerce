# Residencia de Software Serratec 2020
## Projeto Backend Java - Ecommerce

Durante a disciplina de Java 2 Backend, foi passado como projeto final a construção de uma pequena api para o projeto de banco de dados apresentado na disciplina de Banco de Dados. Como cada um dos alunos/grupos acabaram desenvolvendo tanto o banco de dados como a api com pequenas diferenças, este projeto que aqui se apresenta tem dois objetivos:

1. Servir como Gabarito para a disciplina de Java 2 Backend (auxiliando os alunos que tiveram alguma dificuldade em implementar todas as funcionalidades)
2. Ser um modelo padrão de api backend a ser utilizado na disciplina de FrontEnd

*Objetivo Bonus: Apresentar alguns conceitos novos aos alunos, bem como dois novos frameworks que aumentam a produtividade no desenvolvimento.*

### Implementação de DTOs

DTO - Data Transfer Objects - é um *design pattern* que ajuda na tarefa de transferir dados entre partes de um sistema. No caso desta aplicação ele é utilizado para transportar os dados da camada service para a camada controller. 

Isso vai ajudar principalmente quando o Jackson, usado pelo Spring Boot, for transformar nossos objetos Java em JSON. Como ele faz uma busca recursiva por atributos na geração do json, vimos que em relacionamentos bidirecionais ele acabava gerando um json recursivo infinito e para impedir isso optamos por utilizar a anotação JsonIgnore.

Enquanto nossos objeto de modelo (@Entity) continuam sendo utilizados para a regra de negócio, os DTOs são usados apenas para externalizar os dados, contendo apenas a informação necessária. É possivel até ter mais de um DTO para o mesmo Entity, no caso deste projeto a entidade Pedido contem dois DTOs, um contendo os itens do pedido e outro sem os itens (utilizado na listagem de pedidos).

Para facilitar a utilização de DTOs foi utizado o framework [Mapstruct](https://mapstruct.org/), ele ajuda bastante no mapeamento dos atributos do objeto de modelo para o DTO. 
No artigo [https://medium.com/dev-cave/mapstruct-mapeando-seus-dtos-para-model-8bc362b628fe](https://medium.com/dev-cave/mapstruct-mapeando-seus-dtos-para-model-8bc362b628fe) é possivel ter uma explicação detalhada de como ele funciona.

Como ele utiliza anotações para a geração de código, é necessário configurar o Eclipse para poder trabalhar com ele corretamente, neste link há informações de como realizar essa configuração: [https://mapstruct.org/documentation/ide-support/](https://mapstruct.org/documentation/ide-support/)


### Geração de Gets, Sets, toString, etc

Gets, Sets, toString, equals, hashcode, etc... são métodos que devemos sempre implementar em nossos objetos java (e mesmo o Eclipse ajudando nessa tarefa, o código fica extremamente poluído, um objeto com 2 atributos acaba tendo dezenas de linhas de código).

Nesse projeto utilizados um framework chamado Lombok que, através de anotações, "injeta" código em nossas classes com a implementação de todos estes métodos, neste link vocês poderam encontrar uma pequena introdução a ele [https://www.baeldung.com/intro-to-project-lombok](https://www.baeldung.com/intro-to-project-lombok).

Na própria página no Lombok há uma pequena introdução em vídeo https://projectlombok.org/. Assim como o mapstruct, é necessário configurar o Eclipse para o perar com ele. Para isso é necessário baixar o jar do Lombok [https://projectlombok.org/download](https://projectlombok.org/download) e executá-lo como uma aplicação java (java -jar lombok.jar). 

Neste link é possível encontrar um tutorial de como utilizar o instalador do lombok: [https://projectlombok.org/setup/eclipse](https://projectlombok.org/setup/eclipse)

Obs: Como os métodos equals, hashcode e toString gerados pelo Lombok utilizam todos os atributos da classe, em alguns casos ele acabou gerando um comportamento recursivo (cliente referencia endereço que referencia cliente). Nestes casos foi usado anotações *@ToString.Exclude* e *@EqualsAndHashCode.Exclude* do próprio lombok

### Pequenas melhorias na documentação Swagger

Neste projeto tambem foi feito uma melhora simples na documentação do Swagger, neste link vocês podem ter mais informações de como melhorar ainda mais esta documentação: [https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger/](https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger/)

O Swagger pode ser acessado na url [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Imagens em Produto

Para o cadastro de produdo foram criados dois endpoints a mais para permitir o upload e acesso a fotos de produto. Também foi adicionado um pequeno frontend em vue.js na pasta static, que pode ser acessado direto pela url [http://localhost:5000](http://localhost:5000).
Ele é composto de apenas dois arquivos uma pagina html e um javascript. Utiliza axios para fazer o upload. É apenas um pequeno exemplo de possibilidade de upload, existem outros (como por exemplo converter a imagem em string base64 e enviá-la como atribudo do JSON).


### Disponibilização On-Line

Temporariamente, esta aplicação tambem está disponível on-line num servidor da aws disponibilizado pela [IDK Digital](https://idk.digital).  

* A documentação Swagger pode ser acessada através da URL [http://residencia-ecommerce.us-east-1.elasticbeanstalk.com/swagger-ui.html](http://residencia-ecommerce.us-east-1.elasticbeanstalk.com/swagger-ui.html)
* O mini frontend de produtos pode ser acessado em [http://residencia-ecommerce.us-east-1.elasticbeanstalk.com/](http://residencia-ecommerce.us-east-1.elasticbeanstalk.com/)
* O console do H2 pode ser acessado em [http://residencia-ecommerce.us-east-1.elasticbeanstalk.com/h2-console](http://residencia-ecommerce.us-east-1.elasticbeanstalk.com/h2-console) com a url de conexão jdbc:h2:mem:testdb




 
