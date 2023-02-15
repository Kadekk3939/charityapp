import { User } from "../user";
import { CharityAction } from "../charity-action/charity-action";

export interface benefApplicationToCharityActionRead {
  user: User,
  charityAction: CharityAction,
  reason: string,
  status: string
}
