export class AutoPolicy {

    vehicleMake: string;
    vehicleModel: string;
    vehicleYear: number;
    vehicleAccidents: number;

    constructor(vehicleMake: string, vehicleModel: string, vehicleYear: number, vehicleAccidents: number) {
        
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleAccidents = vehicleAccidents;
    }
}