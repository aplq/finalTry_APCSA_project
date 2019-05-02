package primary;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class SqlBase {
    protected final Connection conn;
    public SqlBase(Connection conn){
        this.conn=conn;
    }
    public abstract void delete() throws SQLException;
    public abstract void addCsv() throws SQLException;
    public abstract void deleteCev() throws SQLException;
}
