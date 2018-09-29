package co.com.tipi.alertbutton.service;

import co.com.tipi.alertbutton.domain.Alert;
import co.com.tipi.alertbutton.domain.AlertButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AlertService {

    private static final Logger logger = LoggerFactory.getLogger(AlertService.class);

    @Autowired
    private Connection mysqlDBConnection;

    @Autowired
    private Connection sqliteDBConnection;


    public List<String> listCall() {
        logger.info("--listCall ");
        List<String> listCall = new ArrayList<>();


        //select
        //String query = "select text_ from public.notification where audit_created_ > current_timestamp - (5 * interval '1 second');";

        String query = "select src" +
                ", clid" +
                ", dst" +
                ", channel" +
                ", did" +
                //", cnom" +
                ", outbound_cnum" +
                ", calldate  " +
                " from cdr " +
                " where calldate > timestampadd(SECOND, -30, current_timestamp);";

        //String query = "select rfam_id from family limit 2;"; //Test
        ResultSet rs = null;
        Statement st = null;
        try {
            st = mysqlDBConnection.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {

                String phone  = rs.getString(1);
                listCall.add(phone);
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

        return listCall;
    }





    public AlertButton findButtonByPhone(String mPhone) {
        logger.info("--findButtonByPhone ");
        AlertButton alertButton = null;
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
                "  FROM Button WHERE phone like '%" + mPhone + "%';";
        ResultSet rs = null;
        Statement st = null;
        try {
            st = sqliteDBConnection.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                alertButton = new AlertButton();
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

    public AlertButton findActiveButtonByPhone(String mPhone) {
        logger.info("--findButtonByPhone ");
        AlertButton alertButton = null;
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
                "  FROM Button WHERE phone = '" + mPhone + "' and status = 'ACTIVE';";
        ResultSet rs = null;
        Statement st = null;
        try {
            st = sqliteDBConnection.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                alertButton = new AlertButton();
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

    public String addAlert(Alert alert) {
        logger.info("--addAlert ");
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*
        String sql = "INSERT INTO Alert (" +
                "                      date," +
                "                      button" +
                "                  ) " +
                "                   VALUES (" +
                "                       current_timestamp," +
                "                       ?" +
                "                   );";
        */
        logger.info("--currentDate " + dateFormat.format(currentDate));
        String sql = "INSERT INTO Alert (" +
                "                      date," +
                "                      button" +
                "                  ) " +
                "                   VALUES (" +
                "                       '" + dateFormat.format(currentDate) + "'," +
                "                       ?" +
                "                   );";

        PreparedStatement pstmt = null;
        try  {

            pstmt = sqliteDBConnection.prepareStatement(sql);
            pstmt.setLong(1, alert.getButton().getId());

            pstmt.executeUpdate();
            logger.info("--Alert recorded");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<Alert> listAlert(String phoneFilter, String nameFilter, String contactNameFilter, String initDateFilter, String endDateFilter) {
        logger.info("--listAlert ");
        List<Alert> alerts = new ArrayList<>();
        //select
        String query = "SELECT a.id" +
                "       , a.date" +
                "       , b.phone" +
                "       , b.name" +
                "       , b.address" +
                "       , b.contactName" +
                "       , b.contactPhone" +
                "       , b.latitud" +
                "       , b.longitude" +
                "       , b.status" +
                "    FROM Alert a INNER JOIN Button b ON a.button = b.id WHERE 1 = 1 ";

        if (phoneFilter != null && phoneFilter.length() > 0) {
            query += " AND b.phone like '%" + phoneFilter + "%'";
        }

        if (nameFilter != null && nameFilter.length() > 0) {
            query += " AND b.name like '%" + nameFilter + "%'";
        }

        if (contactNameFilter != null && contactNameFilter.length() > 0) {
            query += " AND b.contactName like '%" + contactNameFilter + "%'";
        }

        if (initDateFilter != null && initDateFilter.length() > 0) {
            query += " AND a.date >= '" + initDateFilter + "'";
        }

        if (endDateFilter != null && endDateFilter.length() > 0) {
            query += " AND a.date <= '" + endDateFilter + "'";
        }

        query += " ORDER BY a.date DESC";

        ResultSet rs = null;
        Statement st = null;
        try {
            st = sqliteDBConnection.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                Alert alert = new Alert();
                Long id  = rs.getLong(1);
                String date = rs.getString(2);
                alert.setId(id);
                alert.setDate(date);

                AlertButton alertButton = new AlertButton();
                String phone        = rs.getString(3);
                String name         = rs.getString(4);
                String address      = rs.getString(5);
                String contactName  = rs.getString(6);
                String contactPhone = rs.getString(7);
                Double latitud      = rs.getDouble(8);
                Double longitude    = rs.getDouble(9);
                String status       = rs.getString(10);

                alertButton.setName(name);
                alertButton.setAddress(address);
                alertButton.setContactName(contactName);
                alertButton.setContactPhone(contactPhone);
                alertButton.setLat(latitud);
                alertButton.setLng(longitude);
                alertButton.setStatus(AlertButton.StatusEnum.fromValue(status));
                alertButton.setPhone(phone);

                alert.setButton(alertButton);

                alerts.add(alert);
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
        return alerts;
    }

}
