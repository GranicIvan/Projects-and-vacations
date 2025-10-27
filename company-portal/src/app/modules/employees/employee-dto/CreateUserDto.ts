import { UserRole } from "../../shared/shared-dto/UserRole";

export interface CreateUserDto {
    id: number;
    firstName: string;
    lastName: string;
    dateOfBirth: Date;
    email: string;
    password: string;
    address: string;
    vacationDaysLeft: number;

    userRole: UserRole;
}