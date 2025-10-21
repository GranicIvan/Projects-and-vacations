import { UserDto } from "../../employees/employee-dto/UserDto";

    export interface VacationShowDto {
        id: number;
        user: UserDto;
        startDate: Date;
        endDate: Date;
        vacationLength: number;
        vacationRequestStatus: VacationRequestStatus;
    }
