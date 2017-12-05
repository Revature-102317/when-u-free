import {FriendGroup} from './friendgroup';
import {TimeSlot} from './timeSlot';

export interface GroupFreeTime {
  groupFreeTimeId: number;
  numUsers: number;
  friendGroup: FriendGroup;
  timeslot: TimeSlot;
}
