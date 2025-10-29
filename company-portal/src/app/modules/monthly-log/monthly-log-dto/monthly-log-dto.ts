import { ProjectAssignmentDto } from '../../project-assignment/project-assingment-dto/project-assingment-dto';

export interface MonthlyLogDto {
  projectAssignment: ProjectAssignmentDto;

  yearMonth: Date;

  hoursWorked: number;
}
