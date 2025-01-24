package flojerasdependency;

import java.sql.*;

//FLOJERAS SQL UTILITY V0.1

/**
 *
 * @author Carlos Madrid
 */
public class FlojerasSQLUtility implements AutoCloseable{
    private Connection con;
    private Statement stmt;
    private String url;
    private String usuario;
    private String pass;
    private boolean verbose;

    public FlojerasSQLUtility(String driver, String host, String base_datos, String usuario, String pass){
        this(driver, host, base_datos, usuario, pass, false);
    }

    public FlojerasSQLUtility(String driver, String host, String base_datos, String usuario, String pass, boolean verbose) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.url = "jdbc:mysql://" + host + "/" + base_datos;
        this.usuario = usuario;
        this.pass = pass;
        this.verbose = verbose;

        establecerConexion();
    }

    private void establecerConexion(){
        try {
            con = DriverManager.getConnection(url, usuario, pass);
            stmt = con.createStatement();
            if(verbose) System.out.println("Conexión establecida correctamente.");
        } catch (SQLException ex) {
            if(verbose){
                System.out.println("No se ha podido establecer la conexión correctamente.");
                ex.printStackTrace(System.out);
            }
        }
    }
    
    public void cerrarConexion(){
        try {
            con.close();
        } catch (SQLException ex) {
            if(verbose){
                System.out.println("No se ha podido cerrar la conexión correctamente.");
                ex.printStackTrace(System.out);
            }
        }
    }
    
    public ResultSet select(String consulta){
        try {
            stmt = con.createStatement();
            if(verbose) System.out.println("Ejecutando consulta -> " + consulta);
            return stmt.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }
    
    public void recorrerConsulta(ResultSet resultadoConsulta){
        try {
            int nColumnas = resultadoConsulta.getMetaData().getColumnCount();
            while(resultadoConsulta.next()){
                for(int i = 1; i <= nColumnas; i++){
                    System.out.print(resultadoConsulta.getString(i));
                    if(i != nColumnas) System.out.print(" - ");
                }
                System.out.println("");
            }
        } catch (SQLException ex) {}
    }
    
    public int persistir(String consulta){
        try {
            stmt = con.createStatement();
            if(verbose) System.out.println("Ejecutando consulta -> " + consulta);
            int rowAff = stmt.executeUpdate(consulta);
            return rowAff;
        } catch (SQLException ex) {
            if(verbose){
                System.out.println("Ha habido un error al ejecutar la consulta.");
                System.out.println(ex.getLocalizedMessage());
                ex.printStackTrace(System.out);
            }
            return 0;
        }
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
