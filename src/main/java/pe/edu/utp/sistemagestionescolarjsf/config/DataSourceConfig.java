package pe.edu.utp.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;

@DataSourceDefinition(
    name = "java:app/jdbc/ColegioDS",
    className = "com.mysql.cj.jdbc.MysqlDataSource",
    url = "jdbc:mysql://localhost:3306/db_colegio?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
    user = "root",
    password = ""
)
@Singleton
public class DataSourceConfig {
    // Esta clase le dice a GlassFish que cree la conexión automáticamente
}