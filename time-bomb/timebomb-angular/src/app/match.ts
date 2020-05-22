import { User } from './user';
import { Card } from './card';

export class Match {
	public state: string;
	public owner: User = new User();
	public players: Array<User> = [];
	public current: User = null;
	public deck: Array<Card> = [];

	constructor(public id?: number, public name?: string, public size?: number) { }
}