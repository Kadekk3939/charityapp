import { User } from "../user";
import { CharityAction } from "../charity-action/charity-action";
import { BenefactorDocument } from "../charity-action/document";

export interface benefApplicationToCharityActionRead {
  benefactor: User,
  charityAction: CharityAction,
  reason: string,
  documents:BenefactorDocument[]
}
