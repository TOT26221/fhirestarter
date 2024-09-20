export class Practitioner {
  constructor(
    public id: string = "",
    public resourceType: string = "Practitioner",
    public identifier?: Array<Identifier>,
    public name?: Array<HumanName>,
    public telecom?: Array<ContactPoint>,
    public active: boolean = false,
    public gender: string = "unknown",
    public birthDate: Date = new Date(1000, 1, 1),
    public address?: Array<Address>,
    public photos?: Array<Attachment>,
    public qualification?: Array<Qualification>,
  ) {}
}
export class HumanName {
  constructor(
    public id: string = "",
    public use: string = "",
    public text: string = "",
    public family: string = "",
    public given: string = "",
    public suffix: string = ""
  ) {}
}
export class Identifier {
  constructor(
    public value: string = ""
  ) {}
}
export class ContactPoint {
  constructor() {}
}
export class Address {
  constructor() {}
}
export class Attachment {
  constructor(
    public data: string = "",
    public contentType: string = ""
  ) {}
}
export class Qualification{
    constructor(
      public id: string = "",
      public code: CodableConcept,
      public period: Period,
      public issuer: string = "",
    ) {}
}
export class Period{
  constructor(
    public id: string = "",
    public start: string = ""
  ) {}
}
export class CodableConcept{
  constructor (
    public id: string = "",
    public text: string = ""
  ){}
}
