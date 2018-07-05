
package principal;

import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.IndexBuilder;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class conexion {
    public Database ComprobationDB() {
        Database db = null;

        String route = System.getProperty("user.dir");
        File directory = new File(route + "\\database");
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }

        try {
            File dbFile = new File(System.getProperty("user.dir") + "\\database\\clinica.accdb");
            if (dbFile.exists() && !dbFile.isDirectory()) {
                db = DatabaseBuilder.open(dbFile);
            } else {
                db = DatabaseBuilder.create(Database.FileFormat.V2007, dbFile);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Can't create or open file \"clinica.accdb\". Check if it permitted by security settings of path/file.\nMore info:\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            if (!db.getTableNames().contains("DB")) {
                Table tblNew = new TableBuilder("DB")
                        .addColumn(new ColumnBuilder("id", DataType.LONG)
                                .setAutoNumber(true))
                        .addColumn(new ColumnBuilder("name", DataType.TEXT))
                        .addColumn(new ColumnBuilder("age", DataType.INT))
                        .addIndex(new IndexBuilder(IndexBuilder.PRIMARY_KEY_NAME)
                                .addColumns("id").setPrimaryKey())
                        .toTable(db);

                // Fill table by data
                /*tblNew.addRow(Column.AUTO_NUMBER, "John", 27);
                tblNew.addRow(Column.AUTO_NUMBER, "Peter", 43);
                tblNew.addRow(Column.AUTO_NUMBER, "Maycon", 21);*/
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    private String database = "clinica.accdb";
    private String username = "";
    private String password = "";
    private String url = "";
    
    public Connection conn = null;
    
    public conexion(){
        this.ComprobationDB();
        url = "jdbc:ucanaccess://"+System.getProperty("user.dir").replace("\\", "/")+"/database/"+
            database;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println("Error al conectar base de datos:"+ex.getMessage());
        }
    }
    
    public Connection  getConnection(){
        return conn;
    }
}
