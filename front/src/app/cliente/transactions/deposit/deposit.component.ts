import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {
  depositValue!: number;
  constructor(
    private http: HttpClient,
    private auth: AuthService,
    private notifyService: NotificationService,
    private router: Router
    ) {}

  ngOnInit(): void {
    this.depositValue = 0;
  }

  async deposit(): Promise<void> {
    let userId: number | undefined = this.auth.getAuth()?.user
    if (userId === undefined) {
      console.log("Usuário não encontrado");
      this.notifyService.showError("", 'Usuário não encontrado');
      this.router.navigate(['/login']);
      return;
    }
    
    this.notifyService.showInfo("", `Depositando ${this.depositValue}...`);
    await this.callApiDeposit(userId);
    this.notifyService.showSuccess("", "Depósito realizado.");
  }

  async callApiDeposit(userId: number) {
    this.http
      .post<Record<string, any>>(
        "https://joaopedromdp-dac-bantads-q99j6vgv9p52x94x-5003.githubpreview.dev/accounts/"+userId+"/deposit",
        { amount: this.depositValue }
      )
      .subscribe((response) => {
        console.log(response);
      });
  }
}
