package com.teamproject1.scuoledevelhope.classes.calendar.meeting.dao;

import com.teamproject1.scuoledevelhope.classes.calendar.meeting.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MeetingDAO extends JpaRepository<Meeting, Long> {

    //tutti i meeting di un user
    @Query(value = "select m.* from meeting m join defaultdb.user_meeting um on m.id_meeting = um.id_meeting join defaultdb.user u on u.id = um.id_user where u.id = :userId", nativeQuery = true)
    List<Meeting> allMeetingByUser(@Param("userId") Long id);

    //tutti i meeting di un user in un intervallo di tempo
    @Query(value = "select m.* from meeting m join defaultdb.user_meeting um on m.id_meeting = um.id_meeting join defaultdb.user u on u.id = um.id_user where u.id = :id and m.start_date > :startDate and m.start_date < :endDate", nativeQuery = true)
    List<Meeting> intervalGetByID(@Param("id") Long id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "select meeting.* from meeting\n" +
            "join user_meeting on user_meeting.id_meeting  = meeting.id_meeting \n" +
            "where user_meeting.id_user = :id\n" +
            "and start_date > :startDate and end_date < :endDate\n" +
            "LIMIT 1" , nativeQuery = true)
    Meeting nextMeetingById(@Param("id") Long id,@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
