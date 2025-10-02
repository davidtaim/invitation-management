package mx.dvdchr.invitation_management.mapper;

import mx.dvdchr.invitation_management.dto.AttendanceResponseDTO;
import mx.dvdchr.invitation_management.model.Attendance;

public class AttendanceMapper {

    public static AttendanceResponseDTO toDto(Attendance attendance, String guestId) {
        var attendanceResponseDTO = new AttendanceResponseDTO();

        attendanceResponseDTO.setGuestId(guestId);
        attendanceResponseDTO.setSeatId(attendance.getSeat().getId().toString());
        attendanceResponseDTO.setSeatNumber(attendance.getSeat().getSeatNumber());
        attendanceResponseDTO.setTableNumber(attendance.getSeat().getTableNumber());
        attendanceResponseDTO.setStatus(attendance.getStatus().name());
        attendanceResponseDTO.setCheckedIn(attendance.getCheckinTime().toString());
        attendanceResponseDTO.setCheckedOut(attendance.getCheckoutTime() == null ? "" : attendance.getCheckoutTime().toString());

        return attendanceResponseDTO;
    }

}
