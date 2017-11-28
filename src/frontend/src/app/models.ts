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
               public responsiblePersonDTOs:ResponsiblePersonDTO[]
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
