package com.kolotree.task1.service.implementation;

import com.kolotree.task1.dto.vacation.VacationRequestDto;
import com.kolotree.task1.dto.vacation.VacationShowDto;
import com.kolotree.task1.dto.vacation.VacationShowWithUserDto;
import com.kolotree.task1.exception.NotEnoughVacationDays;
import com.kolotree.task1.mapper.VacationMapper;
import com.kolotree.task1.model.User;
import com.kolotree.task1.model.VacationRequest;
import com.kolotree.task1.model.VacationRequestStatus;
import com.kolotree.task1.repository.VacationRequestRepository;
import com.kolotree.task1.service.interfaces.UserService;
import com.kolotree.task1.service.interfaces.VacationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VacationServiceImpl implements VacationService {

    private static final Logger logger = LogManager.getLogger(VacationServiceImpl.class);
    private final VacationRequestRepository vacationRepository;
    private final UserService userService;

    @Override
    public List<VacationShowDto> myVacations() {
        List<VacationRequest> vacationRequestList = vacationRepository.findByUser(userService.getCurrentUser());
        return VacationMapper.toShowDtoList(vacationRequestList);
    }

    @Override
    public void setVacationRequestStatus(VacationRequestStatus vacationRequestStatus, Long vacationRequestId) {

        vacationRepository.updateRequestStatus(vacationRequestStatus, vacationRequestId);

        if (vacationRequestStatus.equals(VacationRequestStatus.APPROVED)) {

            VacationRequest vacationRequest = vacationRepository.findById(vacationRequestId);
            userService.useVacation(vacationRequest.getUser().getId(), vacationRequest.getVacationLength());
        }
    }

    @Override
    public List<VacationShowWithUserDto> getAwaitingVacationRequests() {

        return VacationMapper.toShowWithUserList(
                vacationRepository.findByVacationRequestStatus(VacationRequestStatus.WAITING)
        );

    }

    @Override
    public List<VacationShowWithUserDto> getAll() {
        return VacationMapper.toShowWithUserList(vacationRepository.findAll());
    }

    @Override
    public VacationShowDto getOne(Integer id) {
        Optional<VacationRequest> vacationRequest = vacationRepository.findById(id);

        if (vacationRequest.isEmpty()) {
            throw new EntityNotFoundException("Entity user with ID " + id + " not found");
        }
        return VacationMapper.toShowDto(vacationRequest.get());
    }

    @Override
    public VacationShowDto requestVacation(VacationRequestDto vacationRequestDto) throws NotEnoughVacationDays {
        User user = userService.getCurrentUser();
        int vacationLength = calculateWorkDaysBetweenDates(vacationRequestDto.getStartDate(), vacationRequestDto.getEndDate());
        VacationRequest request = VacationRequest
                .builder()
                .startDate(vacationRequestDto.getStartDate())
                .endDate(vacationRequestDto.getEndDate())
                .user(user)
                .vacationLength(vacationLength)
                .vacationRequestStatus(VacationRequestStatus.WAITING)
                .build();


        //Checking if Employee has enough vacation days to take these days
        if (user.getVacationDaysLeft() < vacationLength) {
            throw new NotEnoughVacationDays();
        }

        return VacationMapper.toShowDto(vacationRepository.save(request));
    }

    private int calculateWorkDaysBetweenDates(Date startDate, Date endDate) {

        // Ensure start <= end
        if (startDate.after(endDate)) {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        int workDays = 0;

        while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            // Skip Saturday (7) and Sunday (1)
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                workDays++;
            }
            calendar.add(Calendar.DATE, 1);
        }

        return workDays;
    }

}
