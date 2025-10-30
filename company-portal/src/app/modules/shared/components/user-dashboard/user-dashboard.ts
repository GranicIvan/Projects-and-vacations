import { Component, inject } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AccountService } from '../../service/account-service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-user-dashboard',
  imports: [ReactiveFormsModule,RouterLink],
  templateUrl: './user-dashboard.html',
  styleUrls: ['./user-dashboard.scss']
})
export class UserDashboard {

    protected accountService = inject(AccountService);
    
}
