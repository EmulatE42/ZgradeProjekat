import construct = Reflect.construct;

export class Address
{
  constructor( public id:number,
               public city:string,
               public number:string,
               public street:string,
               public postalCode:string,
               public country:string,
  ) {}
}

export class LocationDTO
{
  constructor( public locationId:number,
               public type:string,
               public buildingId:number,
               public floor:number,
               public square:number,
               public numberOfFloors:number,
               public tenantDTOs:TenantDTO[]
  ) {}
}

export class BuildingDTO
{
  constructor( public id:number,
               public dateOfConstruction:Date,
               public adress:Address,
               public locations:LocationDTO[],
               public responsiblePersonDTOs:ResponsiblePersonDTO[],
               public buildingManager:TenantDTO,
               public parliamentDTO:ParliamentDTO
  ) {}
}

export class BuildingListItemDTO
{
  constructor( public id:number,
               public city:string,
               public street:string,
               public number:string
  ) {}
}

export class TenantDTO
{
  constructor( public id:number,
               public username:string,
               public firstname:string,
               public lastname:string,
               public isBuildingManager:boolean
  ) {}
}

export class InstitutionDTO
{
  constructor( public id:number,
               public username:string,
               public address:Address,
               public name:string
  ) {}
}

export class ResponsiblePersonDTO
{
  constructor( public id:number,
               public tenantDTO:TenantDTO,
               public institutionDTO:InstitutionDTO,
               public isTenant:boolean,
               public description:string
  ) {}
}

export class BugDTO
{
  constructor( public id:number,
               public description:string,
               public dateOfBug:Date,
               public comments:CommentDTO[],
               public finished:boolean,
               public responsiblePersonDTO:ResponsiblePersonDTO,
               public responsibleFirm:FirmDTO,
               public paid:boolean
  ) {}
}

export class CommentDTO
{
  constructor( public id:number,
               public text:string,
               public user:UserDTO
  ) {}
}

export class UserDTO
{
  constructor( public id:number,
               public username:string
  ) {}
}

export class BillItem
{
  constructor( public id:number,
               public name:string,
               public description:string,
               public quantity:number,
               public amount:number
  ) {}
}

export class Bill
{
  constructor( public id:number,
               public billItemSet:BillItem[],
               public date:Date
  ) {}
}

export class FirmDTO
{
  constructor( public id:number,
               public username:string,
               public address:Address,
               public name:string,
               public description:string
  ) {}
}

export class ParliamentDTO
{
  constructor( public id:number,
               public sessions:SessionDTO[],
               public buildingAddress: AddressDTO
  ) {}
}

export class Parliament
{
  constructor( public id:number,
               public sessions:SessionDTO[]
  ) {}
}

export class SessionDTO
{
  constructor( public id:number,
               public date:Date,
               public topics:TopicDTO[],
               public record:string,
               public timetable: string,
               public creator: UserDTO,
               public parliament: Parliament
  ) {}
}

export class TopicDTO
{
  constructor( public id:number,
               public description:string,
               public creator: UserDTO,
               public accepted: boolean,
               public votes: number
  ) {}
}

export class AddressDTO
{
  constructor( public id:number,
               public number:string,
               public street: string,
               public postalCode: string,
               public country: string
  ) {}
}

export class Link
{
  constructor( public text: string,
               public routerLink: string
  ){}
}

export class LoginResponseDTO
{
  constructor( public text: string,
               public routerLink: string,
               public token: string,
               public id: number,
               public username: string,
               public role: string,
               public isResponsible: boolean
  ){}
}
