import { User } from "../user";
import { CharityAction } from "../charity-action/charity-action";

export interface benefApplicationToCharityActionRead {
  benefactor: User,
  charityAction: CharityAction,
  reason: string,
}
