/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JOptionPane;

/**
 *
 * @author Familia
 */
public class connection {
       
    public Database ComprobationDB() {
        Database db = null;

        String route = "C:\\Users\\" + System.getProperty("user.name");
        File directory = new File(route + "\\Desktop\\javaProyect\\database");
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }

        try {
            File dbFile = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\javaProyect\\database\\clinica.accdb");
            if (dbFile.exists() && !dbFile.isDirectory()) {
                db = DatabaseBuilder.open(dbFile);
            } else {
                db = DatabaseBuilder.create(Database.FileFormat.V2007, dbFile);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Can't create or open file \"testDB.accdb\". Check if it permitted by security settings of path/file.\nMore info:\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            if (!db.getTableNames().contains("DB")) {
                Table tblNew = new TableBuilder("DB")
                        .addColumn(new ColumnBuilder("id", DataType.LONG)
                                .setAutoNumber(true))
                        .addColumn(new ColumnBuilder("nombre", DataType.TEXT))
                        .addColumn(new ColumnBuilder("apellido", DataType.TEXT))
                        .addColumn(new ColumnBuilder("dni", DataType.INT))
                        .addColumn(new ColumnBuilder("fecha", DataType.SHORT_DATE_TIME))
                        .addColumn(new ColumnBuilder("ecivil", DataType.TEXT))
                        .addColumn(new ColumnBuilder("edad", DataType.INT))
                        .addColumn(new ColumnBuilder("genero", DataType.TEXT))
                        .addColumn(new ColumnBuilder("alergia", DataType.TEXT))
                        .addColumn(new ColumnBuilder("departamento", DataType.TEXT))
                        .addColumn(new ColumnBuilder("provincia", DataType.TEXT))
                        .addColumn(new ColumnBuilder("distrito", DataType.INT))
                        .addColumn(new ColumnBuilder("direccion", DataType.TEXT))
                        .addColumn(new ColumnBuilder("correo", DataType.TEXT))
                        .addColumn(new ColumnBuilder("telefono", DataType.INT))
                        .addColumn(new ColumnBuilder("altura", DataType.DOUBLE))
                        .addColumn(new ColumnBuilder("peso", DataType.DOUBLE))
                        .addIndex(new IndexBuilder(IndexBuilder.PRIMARY_KEY_NAME)
                                .addColumns("id").setPrimaryKey())
                        .toTable(db);

            }
        } catch (IOException e) {
        }
        return null;
    }
    
    Connection connect;

    public Connection conexion() {
        try {
            String routeFile = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\db\\db.accdb";
            String Url = "jdbc:ucanaccess://" + routeFile;
            connect = DriverManager.getConnection(Url);

        } catch (Exception e) {
        }

        return connect;
    }
}
