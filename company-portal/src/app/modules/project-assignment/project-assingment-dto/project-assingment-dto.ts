import { UserDto } from "../../employees/employee-dto/UserDto";
import { ProjectShowDto } from "../../projects/project-dto/ProjectShowDto";

export interface ProjectAssignmentDto {
    id: number;
    user: UserDto;
    project: ProjectShowDto;
    hourlyPay: number;

}

