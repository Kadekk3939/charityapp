export class User {
    userId:number;
    firstName: string;
    lastName: string;
    login: string;
    password: string;
    email: string;
    userRole: string;
    /**
     * name
     */
    public isBenefactor() {
        if(this.userRole=="Benefactor")
            return true;
        else
            return false
    }
}
