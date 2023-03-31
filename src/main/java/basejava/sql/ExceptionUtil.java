package basejava.sql;

import basejava.exception.ExistStorageException;
import basejava.exception.StorageException;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;

public class ExceptionUtil {

  private ExceptionUtil() {
  }

  public static StorageException convertException(SQLException e) {
    if (e instanceof PSQLException) {

//      http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
      if (e.getSQLState().equals("23505")) {
        return new ExistStorageException(null);
      }
    }
    return new StorageException(e);
  }

}
