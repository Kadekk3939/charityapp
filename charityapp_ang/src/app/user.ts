export class User {
    userId: number;
    firstName: string;
    lastName: string;
    login: string;
    password: string;
    email: string;
    role: string;

    public isBenefactor(): boolean {
        if (this.role == "Benefactor") {
            return true;
        }
        else {
            return false
        }
    }
}
