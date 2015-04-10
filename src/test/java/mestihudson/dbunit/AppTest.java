package mestihudson.dbunit;

import org.junit.Test;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.DriverManager;

public class AppTest {
  @Test public void extract_from_db() throws Throwable {
    Class.forName("org.h2.Driver");

    IDatabaseConnection connection = new DatabaseConnection(DriverManager.getConnection("jdbc:h2:tcp://localhost/~/Downloads/maven/db/unit", "sa", ""));
    // QueryDataSet qds = new QueryDataSet(connection);
    // qds.addTable("recipe", "SELECT * FROM recipe where is_deleted != 'Y'");
    // qds.addTable("recipe_ext_xref");
    // FlatXmlDataSet.write(qds, new FileOutputStream("partial-dataset.xml"));

    IDataSet fds = connection.createDataSet();
    FlatXmlDataSet.write(fds, new FileOutputStream("target/full-dataset.xml"));
  }
}
