package com.kolotree.task1.service.implementation;

import com.kolotree.task1.dto.Vacation.VacationRequestDto;
import com.kolotree.task1.dto.Vacation.VacationShowDto;
import com.kolotree.task1.exception.NotEnoughVacationDays;
import com.kolotree.task1.mapper.VacationMapper;
import com.kolotree.task1.model.User;
import com.kolotree.task1.model.VacationRequest;
import com.kolotree.task1.model.VacationRequestStatus;
import com.kolotree.task1.repository.VacationRequestRepository;
import com.kolotree.task1.service.interfaces.UserService;
import com.kolotree.task1.service.interfaces.VacationService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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


        if (user.getVacationDaysLeft() < vacationLength) {
            throw new NotEnoughVacationDays();
        }
        userService.useVacation(user.getId(), vacationLength);
        user.setVacationDaysLeft(user.getVacationDaysLeft() - vacationLength);
        request.setUser(user);

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
