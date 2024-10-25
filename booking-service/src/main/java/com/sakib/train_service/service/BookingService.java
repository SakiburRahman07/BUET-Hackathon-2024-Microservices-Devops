package com.sakib.train_service.service;

import com.sakib.train_service.OTPClient;
import com.sakib.train_service.dao.BookDao;
import com.sakib.train_service.dao.SeatDao;
import com.sakib.train_service.model.Booking;
import com.sakib.train_service.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class BookingService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private OTPClient otpClient;


    public Booking addBooking(Booking booking) throws Exception {
        // Retrieve seat information
        Optional<Seat> seatOpt = seatDao.findByTrainIdAndDate(booking.getTrainid(), booking.getDate());

        if (seatOpt.isPresent()) {
            Seat seat = seatOpt.get();
            int availableSeat = seat.getAvailableSeat();
            int bookingSeats = Integer.parseInt(booking.getBookingseat());

            if (availableSeat >= bookingSeats) {
                // Update available seats
                seat.setAvailableSeat(availableSeat - bookingSeats);
                seatDao.save(seat);

                // Save the booking and mark it as "pending" for OTP verification
                booking.setOtpStatus(false);
                Booking savedBooking = bookDao.save(booking);

                // Start the OTP verification process with a timer
                startOTPVerificationProcess(savedBooking);

                return savedBooking;
            } else {
                throw new Exception("Not enough available seats");
            }
        } else {
            throw new Exception("No seat information found for the specified train and date");
        }
    }

    @Async
    public void startOTPVerificationProcess(Booking booking) {
        // Simulate calling OTP service and starting the verification process
        CompletableFuture.runAsync(() -> {
            try {
                // Wait for OTP validation (Simulate a timer, e.g., 2 minutes)
                Thread.sleep(120000); // 2 minutes timer

                // Check the OTP status after the timer

                Booking b1 = bookDao.findById(booking.getId()).orElseThrow(() -> new RuntimeException("Booking not found with ID: " + booking.getId()));
                Boolean x = b1.isOtpStatus();

                if (!x) {
                    cancelBooking(booking);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void verifyOTP(Booking booking, int otp) throws Exception {
        String otpResponse = otpClient.getPayment(otp);
        if ("Payment Successful".equals(otpResponse)) {
            booking.setOtpStatus(true);
            bookDao.save(booking);
        } else {
            throw new Exception("OTP Verification Failed");
        }
    }

    public void cancelBooking(Booking booking) {
        // Cancel the booking and release the seats
        Optional<Seat> seatOpt = seatDao.findByTrainIdAndDate(booking.getTrainid(), booking.getDate());
        if (seatOpt.isPresent()) {
            Seat seat = seatOpt.get();
            int availableSeat = seat.getAvailableSeat();
            int bookingSeats = Integer.parseInt(booking.getBookingseat());

            // Increase available seats
            seat.setAvailableSeat(availableSeat + bookingSeats);
            seatDao.save(seat);

            // Delete the booking
            bookDao.delete(booking);
        }
    }

    public Booking getBookingById(Long bookingId) throws Exception {
        Optional<Booking> bookingOpt = bookDao.findById(bookingId);
        if (bookingOpt.isPresent()) {
            return bookingOpt.get();
        } else {
            throw new Exception("Booking not found with ID: " + bookingId);
        }
    }

//    public Booking addBooking(Booking booking) throws Exception {
//        // Retrieve the seat information for the specific train and date
//        Optional<Seat> seatOpt = seatDao.findByTrainIdAndDate(booking.getTrainid(), booking.getDate());
//        System.out.println(seatOpt);
//        System.out.println(seatOpt.isPresent());
//
//        if (seatOpt.isPresent()) {
//            Seat seat = seatOpt.get();
//            int availableSeat = seat.getAvailableSeat();
//            int bookingSeats = Integer.parseInt(booking.getBookingseat());  // Convert bookingseat to an integer
//
//            // Check if there are enough available seats
//            if (availableSeat >= bookingSeats) {
//                // Update the available seats
//                seat.setAvailableSeat(availableSeat - bookingSeats);
//                seatDao.save(seat);  // Save the updated seat information
//                System.out.println("yes");
//
//                // Save the booking
//                return bookDao.save(booking);
//            } else {
//                // Not enough seats available
//                throw new Exception("Not enough available seats");
//            }
//        } else {
//            // No seat information found for the specific train and date
//            throw new Exception("No seat information found for the specified train and date");
//        }
//    }

    public Integer getAvailableSeats(Long trainId, String date) {
        Optional<Seat> seatOpt = seatDao.findByTrainIdAndDate(trainId, date);

        // If seat information is available, return the available seat count
        if (seatOpt.isPresent()) {
            return seatOpt.get().getAvailableSeat();
        }

        // If no seat data is found, return 0 or throw an exception
        return 0;
    }
}
