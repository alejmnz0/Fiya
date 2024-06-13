export class AddPlayer {
    teamId: number;
    playerId: string;
    constructor(teamId: number, playerId: string) {
        this.playerId = playerId;
        this.teamId = teamId;
    }
}