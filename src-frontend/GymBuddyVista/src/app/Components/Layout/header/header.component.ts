import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/Services/AuthService/auth.service';
import { TokenService } from 'src/app/Services/TokenService/token.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  email: string = '';

  constructor(private service: AuthService,
    private tokenService: TokenService,
    private route: ActivatedRoute,
    private activateRoute: ActivatedRoute
  ) { }


  type = ''
  login: boolean = false;
  isMenuOpen: boolean = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
  logout() {
    this.service.logout()
    window.location.reload();
  }
  ngOnInit() {
    if (sessionStorage.getItem('roles') === '["ROL_ADMIN"]') {
      this.type = 'admin'
    }
    if (sessionStorage.getItem('token')) {
      this.login = true;
    }
    const roles = this.tokenService.getRoles();
    this.route.params.subscribe((params) => {
      this.email = params['email']; // Obtener el DAS del parámetro de la URL
    });
  }

  
}
