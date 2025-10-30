export interface UpdateUserDto {
    id: number | undefined;
    firstName: string | undefined;
    lastName: string | undefined;
    dateOfBirth: Date | undefined;
    email: string | undefined;
    address: string | undefined;
    vacationDaysLeft: number | undefined;

}