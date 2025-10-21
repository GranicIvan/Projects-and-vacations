import { UserRole } from "../../shared/shared-dto/UserRole";

export interface UserDto {
    id: number;
    firstName: string;
    lastName: string;
    dateOfBirth: Date;
    email: string;
    address: string;
    vacationDaysLeft: number;

    userRole: UserRole;
}