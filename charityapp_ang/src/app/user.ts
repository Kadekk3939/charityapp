import { UserRole } from "./user-role";

export interface User{
    id:number;
    first_name: string;
    last_name: string;
    login: string;
    password: string;
    email: string;
    // nip: string;
    // role_id:UserRole;
}