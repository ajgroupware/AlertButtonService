package co.com.tipi.alertbutton.service;

import co.com.tipi.alertbutton.domain.AlertButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParameterService {

    private static final Logger logger = LoggerFactory.getLogger(ParameterService.class);

    @Autowired
    private Connection sqliteDBConnection;

    public String addButton(AlertButton alertButton) {
        logger.info("--addButton ");
        String response = "OK";
        String sql = "INSERT INTO Button (" +
                "                       phone," +
                "                       name," +
                "                       address," +
                "                       contactName," +
                "                       contactPhone," +
                "                       latitud," +
                "                       longitude," +
                "                       status" +
                "                   ) " +
                "                   VALUES (" +
                "                       ?," +
                "                       ?," +
                "                       ?," +
                "                       ?," +
                "                       ?," +
                "                       ?," +
                "                       ?," +
                "                       ?" +
                "                   );";

        PreparedStatement pstmt = null;
        try  {

            pstmt = sqliteDBConnection.prepareStatement(sql);
            pstmt.setString(1, alertButton.getPhone());
            pstmt.setString(2, alertButton.getName());
            pstmt.setString(3, alertButton.getAddress());
            pstmt.setString(4, alertButton.getContactName());
            pstmt.setString(5, alertButton.getContactPhone());
            pstmt.setDouble(6, alertButton.getLat());
            pstmt.setDouble(7, alertButton.getLng());
            pstmt.setString(8, alertButton.getStatus().name());

            pstmt.executeUpdate();
            logger.info("--AlertButton recorded");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    public String updateButton(AlertButton alertButton) {
        logger.info("--updateButton ");
        String response = "OK";
        String sql = "UPDATE Button SET " +
                "            phone        = ?," +
                "            name         = ?," +
                "            address      = ?," +
                "            contactName  = ?," +
                "            contactPhone = ?," +
                "            latitud      = ?," +
                "            longitude    = ?," +
                "            status       = ?" +
                "     WHERE id = ?;";

        PreparedStatement pstmt = null;
        try  {
            pstmt = sqliteDBConnection.prepareStatement(sql);
            pstmt.setString(1, alertButton.getPhone());
            pstmt.setString(2, alertButton.getName());
            pstmt.setString(3, alertButton.getAddress());
            pstmt.setString(4, alertButton.getContactName());
            pstmt.setString(5, alertButton.getContactPhone());
            pstmt.setDouble(6, alertButton.getLat());
            pstmt.setDouble(7, alertButton.getLng());
            pstmt.setString(8, alertButton.getStatus().name());
            pstmt.setInt(9, alertButton.getId().intValue());

            pstmt.executeUpdate();
            logger.info("--AlertButton updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    public List<AlertButton> listButton(String phoneFilter, String nameFilter, String contactNameFilter) {
        logger.info("--listButton ");
        List<AlertButton> alertButtons = new ArrayList<>();
        //select
        String query = "SELECT id," +
                "       phone," +
                "       name," +
                "       address," +
                "       contactName," +
                "       contactPhone," +
                "       latitud," +
                "       longitude," +
                "       status" +
                "  FROM Button where 1 = 1 ";

        if (phoneFilter != null && phoneFilter.length() > 0) {
            query += " AND phone like '%" + phoneFilter + "%'";
        }

        if (nameFilter != null && nameFilter.length() > 0) {
            query += " AND name like '%" + nameFilter + "%'";
        }

        if (contactNameFilter != null && contactNameFilter.length() > 0) {
            query += " AND contactName like '%" + contactNameFilter + "%'";
        }

        ResultSet rs = null;
        Statement st = null;
        try {
            st = sqliteDBConnection.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                AlertButton alertButton = new AlertButton();
                Long id             = rs.getLong(1);
                String phone        = rs.getString(2);
                String name         = rs.getString(3);
                String address      = rs.getString(4);
                String contactName  = rs.getString(5);
                String contactPhone = rs.getString(6);
                Double latitud      = rs.getDouble(7);
                Double longitude    = rs.getDouble(8);
                String status       = rs.getString(9);

                alertButton.setId(id);
                alertButton.setName(name);
                alertButton.setAddress(address);
                alertButton.setContactName(contactName);
                alertButton.setContactPhone(contactPhone);
                alertButton.setLat(latitud);
                alertButton.setLng(longitude);
                alertButton.setStatus(AlertButton.StatusEnum.fromValue(status));
                alertButton.setPhone(phone);

                alertButtons.add(alertButton);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return alertButtons;
    }

    public AlertButton getButtonByPhone(String pPhone) {
        logger.info("--getButtonByPhone ");
        AlertButton alertButton = new AlertButton();
        //select
        String query = "SELECT id," +
                "       phone," +
                "       name," +
                "       address," +
                "       contactName," +
                "       contactPhone," +
                "       latitud," +
                "       longitude," +
                "       status" +
                "  FROM Button where phone = '" + pPhone.trim() + "';";
        ResultSet rs = null;
        Statement st = null;
        try {
            st = sqliteDBConnection.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                Long id             = rs.getLong(1);
                String phone        = rs.getString(2);
                String name         = rs.getString(3);
                String address      = rs.getString(4);
                String contactName  = rs.getString(5);
                String contactPhone = rs.getString(6);
                Double latitud      = rs.getDouble(7);
                Double longitude    = rs.getDouble(8);
                String status       = rs.getString(9);

                alertButton.setId(id);
                alertButton.setName(name);
                alertButton.setAddress(address);
                alertButton.setContactName(contactName);
                alertButton.setContactPhone(contactPhone);
                alertButton.setLat(latitud);
                alertButton.setLng(longitude);
                alertButton.setStatus(AlertButton.StatusEnum.fromValue(status));
                alertButton.setPhone(phone);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return alertButton;
    }

}
