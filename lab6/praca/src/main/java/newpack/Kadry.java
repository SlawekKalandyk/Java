package newpack;

import java.sql.*;
import java.util.LinkedList;

public class Kadry {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String databaseName = "Firma";
    private String tableName = "kadra";
    private int columnAmount = 2;

    public Kadry() {
        polacz();
        //utworzBazeDanychJesliNieIstnieje(); //access denied
        utworzTabeleJesliNieIstnieje();
    }

    private void polacz() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/skalandy",
                    "skalandy", "MynnMfGjzaF2WNnV");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void zamknijPolaczenie() {
        try {
            if (!conn.isClosed())
                conn.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void wyswietlZawartoscTabeli() {
        try {
            String query = "SELECT * FROM " + tableName + ";";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                for (int i = 1; i <= columnAmount; ++i)
                    System.out.print(rs.getString(i) + " | ");

                System.out.println();
            }
        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }
    }

    private void utworzBazeDanychJesliNieIstnieje() {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName + ";");
        } catch (SQLException sqlEx) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }
    }

    private void utworzTabeleJesliNieIstnieje() {
        try {
            stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS " + tableName
                    + " (pesel CHAR(11) NOT NULL,"
                    + "wynagrodzenieBrutto DECIMAL(8, 2) NOT NULL,"
                    + "PRIMARY KEY (pesel));");
        } catch (SQLException sqlEx) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }
    }

    public void usunTabele() {
        try {
            stmt = conn.createStatement();
            stmt.execute("DROP TABLE " + tableName + ";");
        } catch (SQLException sqlEx) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }
    }

    public void dodajPracownika(Pracownik pracownik) {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO " + tableName + " values ('"
                    + pracownik.getPeselStr() + "','"
                    + pracownik.getWynagrodzenieBrutto().toString() + "');");
        } catch (SQLException sqlEx) {

        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }
    }

    public void znajdzPracownika(String pesel) {
        try {
            String query = "SELECT * FROM " + tableName + " WHERE pesel like '" + pesel + "';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                for (int i = 1; i <= columnAmount; ++i)
                    System.out.print(rs.getString(i) + " | ");

                System.out.println();
            }
        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }
    }

    public void usunPracownika(String pesel) {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM " + tableName
                    + " WHERE pesel = " + pesel + ";" );
        } catch (SQLException sqlEx) {

        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }
    }

    public void zmienWynagrodzenieBruttoPracownika(String pesel, Double noweWynagrodzenie) {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE " + tableName
                    + " SET wynagrodzenieBrutto = " + noweWynagrodzenie.toString()
                    + " WHERE pesel like '" + pesel + "';");
        } catch (SQLException sqlEx) {

        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }
    }

    public Double wynagrodzenieBruttoPracownika(String pesel) {
        Double temp = 0.0;
        try {
            String query = "SELECT * FROM " + tableName + " WHERE pesel like '" + pesel + "';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            temp = Double.parseDouble(rs.getString(2));
        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }
        return temp;
    }

    /*
    public LinkedList<Pracownik> sortowaniePracownikow() {
        LinkedList<Pracownik> pracownicyTemp = new LinkedList<>(pracownicy);
        pracownicyTemp.sort(new SortPracownikow());
        return pracownicyTemp;
    }
    */
}
