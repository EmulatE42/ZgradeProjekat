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

export class BuildingDTO
{
  constructor( public id:number,
               public dateOfConstruction:Date,
               public adress:Address
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
