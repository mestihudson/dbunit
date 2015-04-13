package mestihudson.dbunit;

import org.junit.Test;
import org.junit.Ignore;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.DriverManager;

public class AppTest {
  @Test public void db_load() throws Throwable {
    DB.fixture("criar-administrador-sistema", 2, "Meu Primeiro Nome", "Qualquer coisa que tenha ' ou até mais como ''");
  }
}
