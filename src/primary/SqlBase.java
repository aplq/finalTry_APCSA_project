package primary;

import java.sql.Connection;

public class SqlBase {
    protected final Connection conn;
    public SqlBase(Connection conn){
        this.conn=conn;
    }
}
