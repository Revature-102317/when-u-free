import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-group-invite-user',
  templateUrl: './group-invite-user.component.html',
  styleUrls: ['./group-invite-user.component.css']
})
export class GroupInviteUserComponent implements OnInit {

    results: Named[];
    
    currentUser: User;
    
    searchStr: string;
    searchType: string = "all";
    
    constructor(private http: HttpClient,
		private userService: UserService,
		private snService: SocialNetworkService) { }

    ngOnInit() {
	this.getCurrentUser();
    }

    onSubmit() {
	var searchQuery = {'term': this.searchStr, 'type': 'user'};
	console.log(JSON.stringify(searchQuery));
	let options = {withCredentials: true};
	this.http.post<Named[]>("http://localhost:8080/search", searchQuery, options)
	    .subscribe(data => {
		this.results = data;
	    });
    }

    getCurrentUser(){
	this.userService.getUser().subscribe(
	    data => {
		this.currentUser = data;
	    });
    }

    onInvite(n: Named){
	this.route.params.subscribe(params =>{
	    let id = +params['id'];
	    this.snService.inviteUser(n, id).subscribe(data => {});
	});
    }
    
}
