package com.version1.frs.controller;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.version1.frs.model.Booking;
import com.version1.frs.repository.BookingRepository;
 
 
@RestController
@CrossOrigin
public class BookingController {
 
	BookingRepository brepo;
 
	@Autowired
    public BookingController(BookingRepository brepo) {
		super();
		this.brepo = brepo;
	}
 
	
	@PostMapping("/createBooking")
	public String docreate(@RequestBody Booking booking) {
		brepo.save(booking);
		return "Booking Object Saved";
	}

	@GetMapping("/findbooking/{bookingId}")
	public Booking dofind(@PathVariable("bookingId") long bookingId) { 
		return brepo.findById(bookingId).get();
    }
 
	@GetMapping("/findallbookings")
	public List<Booking> dofindall(){
		List<Booking> list=new ArrayList<Booking>();
		Iterator<Booking> it=brepo.findAll().iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}


}
