import { User } from "../user"
import { CharityAction } from "./charity-action"

export interface aplicationToCharityAction {
    reason:string,
    charityActionName:string
  }
  export interface aplicationToCharityActionRead{
    user:User,
    charityAction:CharityAction,
    reason:string,
    charityActionName:string,
    aplicationStatus: string
  }
  