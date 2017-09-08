//Created by ODI Studio
import groovy.sql.Sql

def db = [url:'jdbc:oracle:thin:@', user:'', password:'', driver:'oracle.jdbc.OracleDriver']
//def ds = odiRef.getJDBCConnection("SRC")
//def con = new Sql(ds)
def con = Sql.newInstance(db.url, db.user, db.password, db.driver)

def rows = con.rows("select * from NDMIS_FOUNDATION.CTL_FILTER_PARAMETERS where 1=1")
assert rows.size() == 2
println rows.join('\n')