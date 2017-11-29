import {FriendsList} from './friendsList'

export interface User {

  userId: number;
  email: string;
  firstname: string;
  lastname: string;
  phone: string;
  friendsList: FriendsList[]
}
