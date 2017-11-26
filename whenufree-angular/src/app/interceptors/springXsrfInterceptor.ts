import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpXsrfTokenExtractor } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SpringXsrfInterceptor implements HttpInterceptor {

    headerName: string = "X-XSRF-TOKEN"
    
    constructor(private tokenService: HttpXsrfTokenExtractor) {}

    
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
	const lcUrl = req.url.toLowerCase();
	
	const token = this.tokenService.getToken();

	// Be careful not to overwrite an existing header of the same name.
	if (token !== null && !req.headers.has(this.headerName)) {
	    req = req.clone({headers: req.headers.set(this.headerName, token)});
	}
	return next.handle(req);
  }
}
