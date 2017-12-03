import {Friendgroup} from './friendgroup';
import {TimeSlot} from './timeSlot';

export interface GroupFreeTime {
  groupFreeTimeId: number;
  numUsers: number;
  friendGroup: Friendgroup;
  timeslot: TimeSlot;
}
