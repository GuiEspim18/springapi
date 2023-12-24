<h1>Configure in your src/main/resources folder:</h1>

<ol>
<li>add the application.properties file</li>
<li>
inside application.properties file paste this code: 
<p>spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true</p>
<p>spring.datasource.url=jdbc:postgresql://localhost:5432/your_path</p>
<p>spring.datasource.username=your_user</p>
<p>spring.datasource.password=your_pass</p>
<p>spring.jpa.hibernate.ddl-auto=update</p>
</li>
<li>put your database username, database password and database path</li>

</ol>

