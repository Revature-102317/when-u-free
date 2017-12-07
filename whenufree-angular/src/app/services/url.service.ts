import { Injectable } from '@angular/core';

@Injectable()
export class UrlService {

    url: string = 'http://ec2-18-216-0-70.us-east-2.compute.amazonaws.com:8080';
    constructor() { }

    getUrl(){
	return this.url;
    }
}
