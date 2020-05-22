export class Card {
	public ownerId: number = 0;
	
	constructor(
		public id?: number,
		public revealed?: boolean,
		public type?: string) { }
}