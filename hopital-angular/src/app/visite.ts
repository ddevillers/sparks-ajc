import { Medecin } from './medecin';
import { Patient } from './patient';

export class Visite {
    public medecin: Medecin;
    public patient: Patient;

    constructor(public id?: number, public prix?: number, public dateVisite?: Date) {
        this.medecin = new Medecin(2);
        this.patient = new Patient(123);
    }
}