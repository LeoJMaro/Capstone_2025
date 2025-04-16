export class HomePolicy {

    dwellingType: string;
    heatingType: string;
    location: string;
    age: number;
    homeValue: number;

    constructor(dwellingType: string, heatingType: string, location: string, age: number, homeValue: number) {
        
        this.dwellingType = dwellingType;
        this.heatingType = heatingType;
        this.location = location;
        this.age = age;
        this.homeValue = homeValue
    }
}