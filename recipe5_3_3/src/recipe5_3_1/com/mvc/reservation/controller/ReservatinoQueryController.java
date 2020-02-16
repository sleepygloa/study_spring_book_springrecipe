package recipe5_3_1.com.mvc.reservation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import recipe5_3_1.com.mvc.reservation.domain.Reservation;
import recipe5_3_1.com.mvc.reservation.service.ReservationService;

@Controller
@RequestMapping("/reservationQuery")
public class ReservatinoQueryController {

	private final ReservationService reservationService;

	public ReservatinoQueryController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}
	
	@GetMapping
	public void setupform() {}
	
	@PostMapping
	public String submitForm(@RequestParam("courtName") String courtName, Model model) {
		//List<Reservation> reservations = java.util.Collections.emptyList();
		List<Reservation> reservations = java.util.Collections.emptyList();
		if(courtName != null) {
			reservations = reservationService.query(courtName);
		}
		model.addAttribute("reservations", reservations);
		return "reservationQuery";
	}
	
	
}
