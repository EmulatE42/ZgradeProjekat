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
               public flor:number,
               public square:number,
               public numberOfFlors:number,
               public tenantDTO:TenantDTO
  ) {}
}

export class BuildingDTO
{
  constructor( public id:number,
               public dateOfConstruction:Date,
               public adress:Address,
               public locations:LocationDTO[]
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
               public lastname:string
  ) {}
}
