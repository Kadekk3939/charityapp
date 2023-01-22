import { User } from "../user"
import { CharityAction } from "./charity-action"

export interface donationToCharityAction {
    charityActionName:string,
    amount:number,
    anonymous:boolean
}