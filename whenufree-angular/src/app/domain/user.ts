import {FriendsList} from './friendsList';
import {Connection} from './connection';

export interface User {


    userId: number;
    email: string;
    firstname: string;
    lastname: string;
    phone: string;
    password: string;
    friendsList: FriendsList[];
    connections: Connection[];
}
