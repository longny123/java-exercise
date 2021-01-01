package coaching.repository;

import coaching.model.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Staff repository
 */
public class StaffRepository implements CrudRepository<Staff> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StaffRepository.class);
    private DataSource dataSource;

    /**
     * Initialize
     *
     * @param dataSource Data source
     */
    public StaffRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Insert a list of staffs into the staff table
     *
     * @param data a list of staffs
     */
    @Override
    public void save(Collection<Staff> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        for (Staff staff : data) {
            String insertQuery = "INSERT INTO staff (id, firstName, middleName, lastName, dob, gender, phone, address)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            try (final Connection connection = this.dataSource.getConnection();
                 final PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                LOGGER.info("Before: " + preparedStatement.toString());
                preparedStatement.setInt(1, staff.getId());
                preparedStatement.setString(2, staff.getFirstName());
                preparedStatement.setString(3, staff.getMiddleName());
                preparedStatement.setString(4, staff.getLastName());
                preparedStatement.setString(5, staff.getDob());
                preparedStatement.setString(6, String.valueOf(staff.getGender()));
                preparedStatement.setString(7, staff.getPhone());
                preparedStatement.setString(8, staff.getAddress());
                LOGGER.info("After: " + preparedStatement.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * Get all staffs from the staff table
     *
     * @return a list of staffs
     */
    @Override
    public Collection<Staff> findAll()  {
        Collection<Staff> staffs = new ArrayList<>();
        String selectQuery = "SELECT * FROM staff;";
        try (
            final Connection connection = this.dataSource.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet rs = statement.executeQuery(selectQuery))
        {
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt(1));
                staff.setFirstName(rs.getString(2));
                staff.setMiddleName(rs.getString(3));
                staff.setLastName(rs.getString(4));
                staff.setDob(LocalDate.parse(rs.getString(5)));
                staff.setGender(rs.getString(6));
                staff.setPhone(rs.getString(7));
                staff.setAddress(rs.getString(8));
                staffs.add(staff);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return staffs;
    }
}
