import { ProjectAssignmentDto } from "../../project-assignment/project-assingment-dto/project-assingment-dto";
import { ProjectShowDto } from "../../projects/project-dto/ProjectShowDto";
import { UserRole } from "../../shared/shared-dto/UserRole";

export interface DetailedUserDto {
    id: number;
    firstName: string;
    lastName: string;
    dateOfBirth: Date;
    email: string;
    address: string;
    vacationDaysLeft: number;

    userRole: UserRole;

    projectAssignment: ProjectAssignmentDto[];
}