package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.CourseOffering;
import com.marius.sms.backend.entities.OfferingSchedule;
import com.marius.sms.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferingScheduleDAO {

    public List<OfferingSchedule> getSchedulesByOfferingIds(List<Integer> offeringIds) {
        if (offeringIds.isEmpty()) return new ArrayList<>();

        List<OfferingSchedule> schedules = new ArrayList<>();

        // Build IN clause dynamically
        String inClause = offeringIds.toString().replace("[", "").replace("]", "");
        String query = String.format("""
            SELECT 
                offering_schedule_id,
                course_offering_id,
                day_of_week,
                start_time,
                end_time,
                room
            FROM sms.offering_schedules
            WHERE course_offering_id IN (%s)
            ORDER BY course_offering_id,
                     CASE 
                       WHEN day_of_week = 'MON' THEN 1
                       WHEN day_of_week = 'TUE' THEN 2
                       WHEN day_of_week = 'WED' THEN 3
                       WHEN day_of_week = 'THU' THEN 4
                       WHEN day_of_week = 'FRI' THEN 5
                       WHEN day_of_week = 'SAT' THEN 6
                       WHEN day_of_week = 'SUN' THEN 7
                     END
        """, inClause);

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
             schedules = processResultSetSchedules(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error fetching offering schedules", ex);
        }

        return schedules;
    }

    /** Private helper to process ResultSet into OfferingSchedule entities */
    private List<OfferingSchedule> processResultSetSchedules(ResultSet rs) throws SQLException {
        List<OfferingSchedule> schedules = new ArrayList<>();
        while (rs.next()) {
            OfferingSchedule os = new OfferingSchedule();
            os.setOfferingScheduleId(rs.getInt("offering_schedule_id"));
            os.setDayOfWeek(rs.getString("day_of_week"));
            os.setStartTime(rs.getTime("start_time").toLocalTime());
            os.setEndTime(rs.getTime("end_time").toLocalTime());
            os.setRoom(rs.getString("room"));

            // Associate CourseOffering ID (only id for mapping in service)
            CourseOffering co = new CourseOffering();
            co.setCourse_offering_id(rs.getInt("course_offering_id"));
            os.setCourseOffering(co);

            schedules.add(os);
        }
        return schedules;
    }
}

