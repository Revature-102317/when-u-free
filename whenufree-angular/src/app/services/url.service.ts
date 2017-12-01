import { Injectable } from '@angular/core';

@Injectable()
export class UrlService {

    url: string = 'http://localhost:8080'
    constructor() { }

    getUrl(){
	return this.url;
    }
}
