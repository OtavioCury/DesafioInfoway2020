<h1>SETUP</h1>

<p>Para inicializar a aplicação é necessário que estejam instaladas as seguintes dependências:</p>

<ul>
	<li><b>JDK - Java 11</b></li>
	<li><b>Maven 3</b></li>
	<li><b>Mysql</b></li>
</ul>

<h2>BANCO DE DADOS</h2>
<p>Após instaladas as dependências, é necessário criar o banco de dados denominado <b>desafioinfoway</b>. Você pode escolher outro nome para o banco e alterar o atributo <b>'spring.datasource.url'</b> presente no arquivo <b>'resources/application.properties'</b>.</p>

<h2>ENVIO DE EMAILS</h2>
<p>Essa aplicação utiliza o Gmail SMTP server para o envio de email na recuperação de senha. As configurações para o envio de email estão no arquivo <b>'resources/application.properties'</b> onde deve-se especificar o seu email e senha nos atributos <b>'spring.mail.username'</b> e <b>'spring.mail.passowrd'</b> respectivamente. O email utilizado deve permitir que aplicações o utilizem para o envio de emails.</p>

<h2>EXECUTANDO</h2>
<p>A aplicação pode ser executada utilizando IDEs adequadas ou pelo seguinte comando no prompt dentro da pasta principal da aplicação: <b>mvn spring-boot:run</b>. Após executar o comando acima a api estará disponível para ser utilizada.</p>