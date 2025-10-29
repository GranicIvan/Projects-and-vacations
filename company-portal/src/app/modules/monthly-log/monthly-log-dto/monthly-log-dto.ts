import { ProjectAssignmentDto } from "../../project-assignment/project-assingment-dto/project-assingment-dto";

export interface MonthlyLogDto {
  id: number;
  projectName: string;
  description: string;

  projectAssignment: ProjectAssignmentDto;

    yearMonth: Date;

    hoursWorked: number;

}
