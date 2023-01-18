import { User } from "../user";
import { CharityAction } from "../charity-action/charity-action";

export interface aplicationToCharityActionRead{
    user:User,
    charityAction:CharityAction,
    reason:string,
    aplicationStatus: string
  }