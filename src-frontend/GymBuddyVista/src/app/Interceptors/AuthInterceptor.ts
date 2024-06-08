import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../Services/TokenService/token.service';

/**
 * A service for intercepting HTTP requests and adding authentication headers.
 * Implements the HttpInterceptor interface to modify outgoing HTTP requests.
 */
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  /**
   * Constructs a new instance of the AuthInterceptor.
   *
   * @param tokenService - The TokenService for retrieving the user's authentication token.
   */
  constructor(private tokenService: TokenService) {}

  /**
   * Intercepts an outgoing HTTP request and adds an authentication header if a token is available.
   *
   * @param req - The outgoing HTTP request.
   * @param next - The next handler in the HTTP request pipeline.
   *
   * @return The modified HTTP request or the original request if no token is available.
   */
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.tokenService.getToken();

    if (token) {
      const cloned = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
      return next.handle(cloned);
    } else {
      return next.handle(req);
    }
  }
}
