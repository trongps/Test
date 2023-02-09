package registration;

import Mylib.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author TrongPS
 */
public class registrationDAO implements Serializable {

    public registrationDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException, NamingException {
        Connection connect = null;
        PreparedStatement stm = null;
        registrationDTO result = null;
        ResultSet rs = null;
        try {

            connect = DBHelper.makeConnection();
            String sql = "select username, [password], fullname "
                    + "from registration "
                    + "where username = ? and password = ?";
            stm = connect.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            
            rs = stm.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("fullname");
                result = new registrationDTO(username, password, fullname, false);
            }
//            rs.close();
//            stm.close();
//            connect.close();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
        return result;
    }

    public boolean checkAccount(String username) {
        boolean r = false;
        try {

            Connection connect = DBHelper.makeConnection();
            String sql = "select username "
                    + "from registration "
                    + "where username = ?";
            PreparedStatement stm = connect.prepareStatement(sql);
            stm.setString(1, username);

            ResultSet result = stm.executeQuery();
            r = result.next();
            result.close();
            stm.close();
            connect.close();
        } catch (Exception e) {
        }
        return r;
    }

    public boolean createAccount(String username, String password, String fullname) {
        boolean r = false;
        try {
            Connection connect = DBHelper.makeConnection();
            if (connect != null) {
                String sql = "insert into registration( "
                        + "	username, "
                        + "	[password], "
                        + "	fullname, "
                        + "	isAdmin "
                        + ") values ( "
                        + "	?, "
                        + "	?, "
                        + "	?, "
                        + "	? "
                        + ")";
                PreparedStatement stm = connect.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, false);

                int rs = stm.executeUpdate();

                if (rs != 0) {
                    r = true;
                } else {
                    r = false;
                }
            }
        } catch (Exception e) {
        }
        return r;
    }
//
//    public String getFullName(String username) {
//        String fullName = "";
//        try {
//            Connection connect = DBHelper.makeConnection();
//            if (connect != null) {
//                String sql = "Select fullname "
//                        + "from registration "
//                        + "where username = ?";
//                PreparedStatement stm = connect.prepareStatement(sql);
//                stm.setString(1, username);
//
//                ResultSet rs = stm.executeQuery();
//                if (rs.next()) {
//                    fullName = rs.getString("fullname");
//                    
//                }
//                rs.close();
//                stm.close();
//                connect.close();
//            }
//        } catch (Exception e) {
//        }
//        return fullName;
//    }

    private List<registrationDTO> list;

    public List<registrationDTO> getList() {
        return list;
    }

    public void search(String value) {
        registrationDTO dto = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            Connection connect = DBHelper.makeConnection();
            if (connect != null) {
                String sql = "select username, [password], fullname, isAdmin "
                        + "from registration "
                        + "where fullname like ?";
                String sql1 = "select username, [password], fullname, isAdmin "
                        + "from registration ";
                stm = connect.prepareStatement(sql);
                stm.setString(1, "%" + value + "%");
                if (stm != null) {
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        dto = new registrationDTO(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getBoolean("isAdmin"));
                        if (list == null) {
                            list = new ArrayList<>();
                        }
                        list.add(dto);
                    }
                }

            }
        } catch (Exception e) {
        }
    }

    public static boolean delete(String username) throws SQLException, ClassNotFoundException {
        Connection connect = null;
        PreparedStatement stm = null;
        try {
            connect = DBHelper.makeConnection();
            if (connect != null) {
                String sql = "Delete From Registration "
                        + "Where username = ?";
                stm = connect.prepareStatement(sql);
                stm.setString(1, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (connect != null) {
                connect.close();

            }
        }
        return false;
    }

    public static boolean update(String username, String password, String fullname) throws SQLException, ClassNotFoundException {
        Connection connect = null;
        PreparedStatement stm = null;
        try {
            connect = DBHelper.makeConnection();
            if (connect != null) {
                String sql = "Update Registration "
                        + "Set password = ?, fullname = ? "
                        + "where username = ?";
                stm = connect.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, fullname);
                stm.setString(3, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (connect != null) {
                connect.close();

            }
        }
        return false;
    }
}
