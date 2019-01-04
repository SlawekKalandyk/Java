package serverpack;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/*
    Planned database:
    2 tables:
    - clients, with fields: | id | password |
    - memes, with fields: | id | name | meme | clientid |
    I may add a 'template' field to 'memes' table to implement
    image search, though that will come later
*/

public class DatabaseCommands {
    private Connection conn = null;
    private String memeExtension = ".jpg";

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            if (conn == null)
                conn = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/skaland1",
                        "skaland1", "XpPpJVpS19kToLBj");
        } catch (SQLException sqlEx) {
            System.out.println("SQLException: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendorError: " + sqlEx.getErrorCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Boolean registerUser(Integer id, String password) {
        if(checkIfUserExistsInDatabase(id, password))
            return false;

        String registerString = "INSERT INTO users VALUES(?, ?);";
        PreparedStatement registerStatement;

        try {
            registerStatement = conn.prepareStatement(registerString);
            registerStatement.setInt(1, id);
            registerStatement.setString(2, password); // password should be hashed!
            registerStatement.executeUpdate();
            registerStatement.close();
            return true;
        } catch (SQLException sqlEx) {
            System.out.println("SQLException: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendorError: " + sqlEx.getErrorCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public Boolean checkIfUserExistsInDatabase(Integer id, String password) {
        String selectString = "SELECT * FROM users WHERE id = ? and password = ?;";
        PreparedStatement selectStatement;
        ResultSet rs;

        try {
            selectStatement = conn.prepareStatement(selectString);
            selectStatement.setInt(1, id);
            selectStatement.setString(2, password);
            rs = selectStatement.executeQuery();

            return rs.next();
        } catch (SQLException sqlEx) {
            System.out.println("SQLException: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendorError: " + sqlEx.getErrorCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }


    /*
        user with id 0 is always in the database (at least that's the assumption)
     */
    public Integer getRegisterId() {
        String selectString = "SELECT * from users";
        PreparedStatement selectStatement;
        ResultSet rs;
        try {
            selectStatement = conn.prepareStatement(selectString);
            rs = selectStatement.executeQuery();
            if(rs.last())
                return rs.getRow();
        } catch (SQLException sqlEx) {
            System.out.println("SQLException: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendorError: " + sqlEx.getErrorCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public Boolean removeUser(Integer id) {
        String removeString = "DELETE FROM users WHERE id = ?;";
        PreparedStatement removeStatement;

        try {
            removeStatement = conn.prepareStatement(removeString);
            removeStatement.setInt(1, id);
            removeStatement.executeUpdate();
            removeStatement.close();
            return true;
        } catch (SQLException sqlEx) {
            System.out.println("SQLException: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendorError: " + sqlEx.getErrorCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public Boolean insertMeme(Integer memeId, String memeName, String memeFilepath, Integer clientId) {
        String insertMemeString = "INSERT INTO memes VALUES(?, ?, ?, ?);";
        PreparedStatement insertMemeStatement;

        try {
            File memeImage = new File(memeFilepath);
            InputStream memeInputStream = new FileInputStream(memeImage);

            insertMemeStatement = conn.prepareStatement(insertMemeString);
            insertMemeStatement.setInt(1, memeId);
            insertMemeStatement.setString(2, memeName);
            insertMemeStatement.setBinaryStream(3, memeInputStream, (int) memeImage.length()); // mediumblob datatype should be enough
            insertMemeStatement.setInt(4, clientId);
            insertMemeStatement.executeUpdate();
            memeInputStream.close();
            insertMemeStatement.close();
            return true;
        } catch (SQLException sqlEx) {
            System.out.println("SQLException: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendorError: " + sqlEx.getErrorCode());
        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public Boolean removeMeme(Integer id) {
        String removeString = "DELETE FROM memes WHERE id = ?;";
        PreparedStatement removeStatement;

        try {
            removeStatement = conn.prepareStatement(removeString);
            removeStatement.setInt(1, id);
            removeStatement.executeUpdate();
            removeStatement.close();
            return true;
        } catch (SQLException sqlEx) {
            System.out.println("SQLException: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendorError: " + sqlEx.getErrorCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public ArrayList<Meme> selectMemesByName(String memeName) {
        String selectString = "SELECT * FROM memes WHERE name = ?";
        ArrayList<Meme> memes = new ArrayList<>();
        PreparedStatement selectStatement;
        ResultSet rs = null;

        try {
            selectStatement = conn.prepareStatement(selectString);
            selectStatement.setString(1, memeName);
            rs = selectStatement.executeQuery();

            while (rs.next()) {
                File meme = new File(memeName + memeExtension);
                InputStream is = rs.getBinaryStream(3);
                OutputStream os = new FileOutputStream(meme);

                int c;
                while ((c = is.read()) > -1) {
                    os.write(c);
                }

                os.close();
                is.close();
                memes.add(new Meme(rs.getInt(1), memeName, meme, rs.getInt(4)));
            }
            rs.close();
        } catch (SQLException sqlEx) {
            System.out.println("SQLException: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendorError: " + sqlEx.getErrorCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null)
                rs = null;
        }

        return memes;
    }

    public ArrayList<Meme> selectMemesByClientId(Integer clientId) {
        String selectString = "SELECT * FROM memes WHERE clientId = ?";
        ArrayList<Meme> memes = new ArrayList<>();
        PreparedStatement selectStatement;
        ResultSet rs = null;

        try {
            selectStatement = conn.prepareStatement(selectString);
            selectStatement.setInt(1, clientId);
            rs = selectStatement.executeQuery();

            while (rs.next()) {
                File meme = new File(clientId + memeExtension);
                InputStream is = rs.getBinaryStream(3);
                OutputStream os = new FileOutputStream(meme);

                int c;
                while ((c = is.read()) > -1) {
                    os.write(c);
                }

                os.close();
                is.close();
                memes.add(new Meme(rs.getInt(1), rs.getString(2), meme, clientId));
            }
            rs.close();
        } catch (SQLException sqlEx) {
            System.out.println("SQLException: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendorError: " + sqlEx.getErrorCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null)
                rs = null;
        }

        return memes;
    }
}
