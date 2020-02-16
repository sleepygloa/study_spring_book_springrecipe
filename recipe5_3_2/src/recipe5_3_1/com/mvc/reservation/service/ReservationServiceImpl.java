package recipe5_3_1.com.mvc.reservation.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import recipe5_3_1.com.mvc.reservation.domain.Player;
import recipe5_3_1.com.mvc.reservation.domain.Reservation;
import recipe5_3_1.com.mvc.reservation.domain.SportType;

@Service
public class ReservationServiceImpl implements ReservationService {

	public static final SportType TENNIS = new SportType(1, "Tennis");
	public static final SportType SOCCER = new SportType(2, "Soccer");

	private final List<Reservation> reservations = new ArrayList<>();

	public ReservationServiceImpl() {
		reservations.add(new Reservation("Tennis : #1", LocalDate.of(2008, 1, 14), 16, new Player("Rogger", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis : #2", LocalDate.of(2008, 1, 14), 20, new Player("James", "N/A"), TENNIS));

	}

	@Override
	public List<Reservation> query(String courtName) {
		// TODO Auto-generated method stub
		return this.reservations.stream().filter(reservation -> reservation.getCourtName().equals(courtName))
				.collect(Collectors.toList());
	}

}
