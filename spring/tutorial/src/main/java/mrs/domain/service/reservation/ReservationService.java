package mrs.domain.service.reservation;

import mrs.domain.model.*;
import mrs.domain.repository.reservation.ReservationRepository;
import mrs.domain.repository.room.ReservableRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {
	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ReservableRoomRepository reservableRoomRepository;

	public List<Reservation> findReservations(ReservableRoomId reservableRoomId) {
		return reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
	}

	public Reservation reserve(Reservation reservation) {
		ReservableRoomId reservableRoomId = reservation.getReservableRoom().getReservableRoomId();

		Optional<ReservableRoom> reservable = reservableRoomRepository.findById(reservableRoomId);
		if (!reservable.isPresent()) {
			throw new UnavailableReservationException("入力の日付・部屋の組み合わせは予約できません");
		}

		boolean overlap = reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId)
				.stream()
				.anyMatch(x -> x.overlap(reservation));

		if (overlap) {
			throw new AlreadyReservedException("入力の時間帯はすでに予約済みです");
		}

		reservationRepository.save(reservation);
		return reservation;
	}

	public void cancel(Integer reservationId, User requestUser) {
		Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);

		reservationOpt.ifPresent(reservation -> {
			if (RoleName.ADMIN != requestUser.getRoleName() && !Objects.equals(reservation.getUser().getUserId(), requestUser.getUserId())) {
				throw new AccessDeniedException("要求されたキャンセルは許可できません");
			}

			reservationRepository.delete(reservation);
		});
	}
}
