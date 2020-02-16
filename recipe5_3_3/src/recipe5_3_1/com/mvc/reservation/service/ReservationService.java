package recipe5_3_1.com.mvc.reservation.service;

import java.util.List;

import recipe5_3_1.com.mvc.reservation.domain.Reservation;

public interface ReservationService {

	public List<Reservation> query(String courtName);
	
}
