import { User } from './user';

export interface Message {
	messageId: number;
	text: string;
	timestamp: string;
	pinned: Boolean 

	author: User;
	poll: Poll;
}
