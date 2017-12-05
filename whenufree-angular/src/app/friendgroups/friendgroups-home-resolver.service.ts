import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import { Injectable }             from '@angular/core';
import { Observable }             from 'rxjs/Observable';
import { Router, Resolve, RouterStateSnapshot,
	ActivatedRouteSnapshot } from '@angular/router';

import { GroupUserService }  from '../services/groupuser.service';
import { FriendGroup } from '../domain/friendgroup';
@Injectable()
export class GroupHomeResolver implements Resolve<FriendGroup> {
	constructor(private gs: GroupUserService, private router: Router) {}

	resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FriendGroup> {
		let id = route.paramMap.get('id');

		return this.gs.postActiveFriendGroup(id).take(1).map( friendGroup => {
			if ( friendGroup) {
				return friendGroup;
			} else { // id not found
				this.router.navigate(['/groups']);
				return null;
			}
		});
	}
}
