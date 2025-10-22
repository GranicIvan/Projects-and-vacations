import { Component, inject } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AccountService } from '../../service/account-service';

@Component({
  selector: 'app-user-dashboard',
  imports: [ReactiveFormsModule],
  templateUrl: './user-dashboard.html',
  styleUrls: ['./user-dashboard.scss']
})
export class UserDashboard {

    protected accountService = inject(AccountService);
    
}
