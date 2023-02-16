import { Donation } from "../donors-donation-history/donation";

export interface CharityAction {
  id: number;
  name: string;
  description: string;
  goal: number;
  endDate: string;
  raised: number;
  topDonors:Donation[];
}
