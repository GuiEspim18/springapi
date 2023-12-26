<h1>Configure in your src/main/resources folder:</h1>

<ol>
<li>add the application.properties file</li>
<li>
inside application.properties file paste this code: 
<p>spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true</br>
spring.datasource.url=jdbc:postgresql://localhost:5432/your_path</br>
spring.datasource.username=your_user</br>
spring.datasource.password=your_pass</br>
spring.jpa.hibernate.ddl-auto=update</br>
# if you need to run the server in another port use this code bellow </br>
# server.port= YOUR_PORT 
</p>
</li>
<li>put your database username, database password and database path</li>

</ol>

