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
               public buildingManager:TenantDTO
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


